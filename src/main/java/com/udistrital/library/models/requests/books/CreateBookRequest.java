package com.udistrital.library.models.requests.books;

public record CreateBookRequest(String title, String description, short publicationYear, boolean available, short categoryId, short authorId) {}