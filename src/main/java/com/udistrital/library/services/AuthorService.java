package com.udistrital.library.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udistrital.library.models.requests.authors.CreateAuthorRequest;
import com.udistrital.library.persistence.entities.Author;
import com.udistrital.library.persistence.repositories.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	private static final String AUTHOR_NOT_FOUND_MSG = "No se encontró un autor con el nombre proporcionado";

	public Author save(CreateAuthorRequest request) {
		Author author = new Author();
		author.setName(request.name());
		author.setCountryOfOrigin(request.countryOfOrigin());
		return authorRepository.save(author);
	}

	public List<Author> findAll() { return authorRepository.findAll(); }

	public Author findByName(String name) throws NoSuchElementException {
		var author = authorRepository.findFirstByNameContaining(name);
		if (author == null) throw new NoSuchElementException(AUTHOR_NOT_FOUND_MSG);
		return authorRepository.findFirstByNameContaining(name);
	}

}
