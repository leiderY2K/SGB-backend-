package org.example.sgb.persistence.repository;

import org.example.sgb.persistence.entity.categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<categoria, Short> {
}
