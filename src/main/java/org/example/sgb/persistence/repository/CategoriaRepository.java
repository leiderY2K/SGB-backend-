package org.example.sgb.persistence.repository;

import org.example.sgb.persistence.entity.Categoria;
import org.example.sgb.persistence.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Short> {

    //@Query("SELECT c.* FROM categoria c WHERE UPPER(unaccent(c.nombrecategoria)) = UPPER(unaccent(?1), , nativeQuery = true)")
    Categoria findByNombreCategoria(String nombreCategoria);
}
