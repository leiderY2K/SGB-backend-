package com.udistrital.library.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.udistrital.library.models.requests.categories.CreateCategoryRequest;
import com.udistrital.library.persistence.entities.Category;
import com.udistrital.library.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<Category> create(@RequestBody CreateCategoryRequest request) {
		Category savedCategory = categoryService.save(request);
		var uriString = String.format("/categories/%s", savedCategory.getName());
		var resourceUri = UriComponentsBuilder.fromUriString(uriString).build().toUri();
		return ResponseEntity.created(resourceUri).body(savedCategory);
	}

	@GetMapping
	public ResponseEntity<List<Category>> getAll() {
		var categories = categoryService.findAll();
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/{category}")
	public ResponseEntity<Category> getByName(@PathVariable("category") String categoryName) {
		var category = categoryService.findByName(categoryName);
		return ResponseEntity.ok(category);
	}

}
