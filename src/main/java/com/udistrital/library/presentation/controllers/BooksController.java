package com.udistrital.library.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udistrital.library.models.dto.books.BookDTO;
import com.udistrital.library.models.requests.books.CreateBookRequest;
import com.udistrital.library.models.requests.books.DeleteBookRequest;
import com.udistrital.library.models.requests.books.UpdateBookRequest;
import com.udistrital.library.persistence.entities.Book;
import com.udistrital.library.services.BookService;

@RestController
@RequestMapping("/books")
public class BooksController {
	@Autowired
	private BookService bookService;

	@PostMapping
	public ResponseEntity<Book> create(@RequestBody CreateBookRequest request) {
		Book savedBook = bookService.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
	}

	@PutMapping()
	public ResponseEntity<Book> update(@RequestBody UpdateBookRequest request) {
		Book bookUpdated = bookService.update(request);
		return ResponseEntity.ok(bookUpdated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Short bookId) {
		DeleteBookRequest request = new DeleteBookRequest(bookId);
		bookService.delete(request);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<BookDTO>> findAll() {
		var books = bookService.findAll();
		return ResponseEntity.ok(books);
	}

	@GetMapping("/{title}")
	public ResponseEntity<List<BookDTO>> findByTitle(@PathVariable("title") String bookTitle) {
		var books = bookService.getByTitle(bookTitle);
		return ResponseEntity.ok(books);
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<BookDTO>> findByCategory(@PathVariable("category") String categoryName) {
		var books = bookService.getByCategory(categoryName);
		return ResponseEntity.ok(books);
	}

	@GetMapping("/author/{author}")
	public ResponseEntity<List<BookDTO>> findByAuthor(@PathVariable("author") String authorName) {
		var books = bookService.getByAuthor(authorName);
		return ResponseEntity.ok(books);
	}

	@GetMapping("/loans/{client}")
	public ResponseEntity<List<BookDTO>> findLoanedByClient(@PathVariable("client") String clientName) {
		var books = bookService.getLoanedByClient(clientName);
		return ResponseEntity.ok(books);
	}

}
