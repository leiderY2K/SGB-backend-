package com.udistrital.library.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udistrital.library.models.requests.categories.CreateCategoryRequest;
import com.udistrital.library.persistence.entities.Category;
import com.udistrital.library.persistence.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	private static final String CATEGORY_NOT_FOUND_MSG = "No se encontró una categoría con el nombre proporcionado";

	public Category save(CreateCategoryRequest request) {
		Category category = new Category();
		category.setName(request.name());
		category.setDescription(request.description());
		return categoryRepository.save(category);
	}

	public List<Category> findAll() { return categoryRepository.findAll(); }

	public Category findByName(String name) throws NoSuchElementException {
		var category = categoryRepository.findFirstByNameContaining(name);
		if (category == null) throw new NoSuchElementException(CATEGORY_NOT_FOUND_MSG);
		return category;
	}

}
