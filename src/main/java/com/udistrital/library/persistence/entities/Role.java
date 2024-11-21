package com.udistrital.library.persistence.entities;

import java.io.Serializable;

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
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 6524090554536958051L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private short id;

	@Column(name = "description")
	private String description;

	public void setId(Short id) { this.id = id; }

	public Short getId() { return id; }

	public void setDescription(String description) { this.description = description; }

	public String getDescription() { return description; }
}
