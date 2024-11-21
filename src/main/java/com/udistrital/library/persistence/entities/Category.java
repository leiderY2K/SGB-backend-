package com.udistrital.library.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Short id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	public void setId(Short id) { this.id = id; }

	public Short getId() { return id; }

	public void setName(String name) { this.name = name; }

	public String getName() { return name; }

	public void setDescription(String description) { this.description = description; }

	public String getDescription() { return description; }

}
