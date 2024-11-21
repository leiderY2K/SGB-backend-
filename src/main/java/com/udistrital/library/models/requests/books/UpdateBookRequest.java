package com.udistrital.library.models.requests.books;

public record UpdateBookRequest(Short id, String title, String description, Short publicationYear, boolean available, Short categoryId, Short authorId) {}
