package com.udistrital.library.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "book")
public class Book implements Serializable {

	private static final long serialVersionUID = -8964397411768835018L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Short id;

	@Column(name = "title")
	private String title;

	@Column(name = "publication_year")
	private Short publicationYear;

	@Column(name = "availability")
	private boolean availability;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "author_id", referencedColumnName = "id")
	private Author author;

	public void setId(Short id) { this.id = id; }

	public Short getId() { return id; }

	public void setTitle(String title) { this.title = title; }

	public String getTitle() { return title; }

	public void setPublicationYear(Short publicationYear) { this.publicationYear = publicationYear; }

	public Short getPublicationYear() { return publicationYear; }

	public void setAvailability(boolean availability) { this.availability = availability; }

	public boolean isAvailable() { return availability; }

	public void setDescription(String description) { this.description = description; }

	public String getDescription() { return description; }

	public void setCategory(Category category) { this.category = category; }

	public Category getCategory() { return category; }

	public void setAuthor(Author author) { this.author = author; }

	public Author getAuthor() { return author; }

}
