package com.udistrital.library.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udistrital.library.persistence.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Short> { Category findFirstByNameContaining(String name); }
