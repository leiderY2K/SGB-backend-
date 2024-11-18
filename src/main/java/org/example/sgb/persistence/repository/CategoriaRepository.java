package org.example.sgb.persistence.repository;

import org.example.sgb.persistence.entity.Categoria;
import org.example.sgb.persistence.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Short> {

}
