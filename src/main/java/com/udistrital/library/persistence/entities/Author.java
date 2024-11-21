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
@Table(name = "author")
public class Author implements Serializable {

	private static final long serialVersionUID = 7015952436823007976L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Short id;

	@Column(name = "name")
	private String name;

	@Column(name = "country_of_origin")
	private String countryOfOrigin;

	public void setId(Short id) { this.id = id; }

	public Short getId() { return id; }

	public void setName(String name) { this.name = name; }

	public String getName() { return name; }

	public void setCountryOfOrigin(String countryOfOrigin) { this.countryOfOrigin = countryOfOrigin; }

	public String getCountryOfOrigin() { return countryOfOrigin; }

}
