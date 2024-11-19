package org.example.sgb.persistence.repository;

import org.example.sgb.persistence.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Short> {
    Autor findByNombre(String nombre);
}
