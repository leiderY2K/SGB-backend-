package com.udistrital.library.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udistrital.library.models.dto.books.BookDTO;
import com.udistrital.library.models.requests.books.CreateBookRequest;
import com.udistrital.library.models.requests.books.DeleteBookRequest;
import com.udistrital.library.models.requests.books.UpdateBookRequest;
import com.udistrital.library.persistence.entities.Author;
import com.udistrital.library.persistence.entities.Book;
import com.udistrital.library.persistence.entities.Category;
import com.udistrital.library.persistence.repositories.AuthorRepository;
import com.udistrital.library.persistence.repositories.BookRepository;
import com.udistrital.library.persistence.repositories.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private AuthorRepository authorRepo;

	private static final String NO_CATEGORY_MSG = "La categoría no existe en la base de datos";

	private static final String NO_AUTHOR_MSG = "El autor no existe en la base de datos";

	private static final String NO_BOOK_MSG = "El libro no existe en la base de datos";

	private static final String NO_BOOK_TO_UPDATE_MSG = "El libro a actualizar no existe en la base de datos";

	private static final String NO_BOOK_TO_DELETE_MSG = "El libro a eliminar no existe en la base de datos";

	@Transactional
	public Book save(CreateBookRequest request) {
		Category category = categoryRepo.findById(request.categoryId()).orElseThrow(() -> new NoSuchElementException(NO_CATEGORY_MSG));
		Author author = authorRepo.findById(request.authorId()).orElseThrow(() -> new NoSuchElementException(NO_AUTHOR_MSG));

		Book book = new Book();
		book.setTitle(request.title());
		book.setDescription(request.description());
		book.setPublicationYear(request.publicationYear());
		book.setAvailability(request.available());
		book.setCategory(category);
		book.setAuthor(author);

		return bookRepo.save(book);
	}

	@Transactional
	public Book update(UpdateBookRequest request) {
		Category category = categoryRepo.findById(request.categoryId()).orElseThrow(() -> new NoSuchElementException(NO_CATEGORY_MSG));
		Author author = authorRepo.findById(request.authorId()).orElseThrow(() -> new NoSuchElementException(NO_AUTHOR_MSG));
		Book bookToUpdate = bookRepo.findById(request.id()).orElseThrow(() -> new NoSuchElementException(NO_BOOK_TO_UPDATE_MSG));

		bookToUpdate.setTitle(request.title());
		bookToUpdate.setDescription(request.description());
		bookToUpdate.setPublicationYear(request.publicationYear());
		bookToUpdate.setAvailability(request.available());
		bookToUpdate.setCategory(category);
		bookToUpdate.setAuthor(author);

		return bookRepo.save(bookToUpdate);
	}

	@Transactional
	public void delete(DeleteBookRequest request) {
		var book = bookRepo.findById(request.id()).orElseThrow(() -> new NoSuchElementException(NO_BOOK_TO_DELETE_MSG));
		bookRepo.delete(book);
	}

	public BookDTO findById(Short id) {
		var book = bookRepo.findById(id).orElseThrow(() -> new NoSuchElementException(NO_BOOK_MSG));
		return convertToDto(book);
	}

	public List<BookDTO> findAll() {
		var books = bookRepo.findAll();
		return books.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<BookDTO> getByCategory(String category) {
		var books = bookRepo.findByCategory(category);
		return books.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<BookDTO> getByAuthor(String author) {
		var books = bookRepo.findByAuthor(author);
		return books.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<BookDTO> getByTitle(String title) {
		var books = bookRepo.findByTitle(title);
		return books.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<BookDTO> getLoanedByClient(String client) {
		var books = bookRepo.findLoanedByClient(client);
		return books.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public BookDTO convertToDto(Book book) { return new BookDTO(book.getTitle(), book.getDescription(), book.getCategory().getName(), book.getAuthor().getName(), book.getPublicationYear()); }
}
