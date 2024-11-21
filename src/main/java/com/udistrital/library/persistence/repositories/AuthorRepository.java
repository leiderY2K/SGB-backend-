package com.udistrital.library.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udistrital.library.persistence.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Short> { Author findFirstByNameContaining(String name); }
