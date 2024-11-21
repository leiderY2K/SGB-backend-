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

import com.udistrital.library.models.requests.authors.CreateAuthorRequest;
import com.udistrital.library.persistence.entities.Author;
import com.udistrital.library.services.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

	@Autowired
	private AuthorService authorService;

	@PostMapping
	public ResponseEntity<Author> create(@RequestBody CreateAuthorRequest request) {
		Author savedAuthor = authorService.save(request);
		var uriString = String.format("/authors/%s", savedAuthor.getName());
		var resourceUri = UriComponentsBuilder.fromUriString(uriString).build().toUri();
		return ResponseEntity.created(resourceUri).body(savedAuthor);
	}

	@GetMapping
	public ResponseEntity<List<Author>> getAll() {
		var authors = authorService.findAll();
		return ResponseEntity.ok(authors);
	}

	@GetMapping("/{author}")
	public ResponseEntity<Author> getByName(@PathVariable("author") String authorName) {
		var author = authorService.findByName(authorName);
		return ResponseEntity.ok(author);
	}
}
