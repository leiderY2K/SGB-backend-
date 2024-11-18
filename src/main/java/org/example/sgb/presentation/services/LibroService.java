package org.example.sgb.presentation.services;

import org.example.sgb.persistence.entity.Libro;
import org.example.sgb.persistence.repository.CategoriaRepository;
import org.example.sgb.persistence.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepo;
    private final CategoriaRepository categoriaRepo;


    public LibroService(LibroRepository libroRepo, CategoriaRepository categoriaRepo) {
        this.libroRepo = libroRepo;
        this.categoriaRepo = categoriaRepo;
    }

    public Libro crearLibro(Libro libro) {
        return libroRepo.save(libro);
    }

    public List<Libro> listarLibrosPorCategoria(String nombreCategoria) {
        return libroRepo.findByCategoria(nombreCategoria);
    }

    public List<Libro> listarLibrosPorAutor(String nombreAutor) {
        String nombreAutorC = "%" + nombreAutor + "%";
        return libroRepo.findByAutor(nombreAutorC);
    }
}
