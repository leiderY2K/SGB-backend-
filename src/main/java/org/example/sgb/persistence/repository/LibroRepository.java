package org.example.sgb.persistence.repository;

import org.example.sgb.persistence.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//public interface LibroRepository extends JpaRepository<Libro, Short> {
//    @Query("SELECT l FROM Libro l JOIN l.categoria c WHERE UPPER((c.nombreCategoria)) = UPPER(?1)")
//    List<Libro> findByCategoria(String nombreCategoria);
//}

@Repository
public interface LibroRepository extends JpaRepository<Libro, Short> {
    @Query(value = "SELECT l.* FROM libro l JOIN categoria c ON l.idcategoriafk = c.idcategoria WHERE UPPER(unaccent(c.nombrecategoria)) = UPPER(unaccent(?1))", nativeQuery = true)
    List<Libro> findByCategoria(String nombreCategoria);


    @Query(value = "SELECT l.* FROM libro l JOIN autor a ON l.idautorfk = a.idautor WHERE UPPER(unaccent(a.nombre)) like UPPER(unaccent(?1))", nativeQuery = true)
    List<Libro> findByAutor(String nombreAutor);

    @Query(value = "SELECT l.* FROM libro l JOIN prestamo_libro pl ON l.idlibro = pl.idlibro JOIN prestamo p ON p.idprestamo = pl.idprestamo JOIN cliente c ON p.idclientefk = c.idcliente  WHERE UPPER(unaccent(c.nombre)) like UPPER(unaccent(?1))", nativeQuery = true)
    List<Libro> findByCliente(String nombreCliente);
}



