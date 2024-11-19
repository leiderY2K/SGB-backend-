package org.example.sgb.presentation.controller;

import org.example.sgb.models.LibroDTO;
import org.example.sgb.persistence.entity.Autor;
import org.example.sgb.persistence.entity.Categoria;
import org.example.sgb.persistence.entity.Libro;
import org.example.sgb.presentation.services.AutorService;
import org.example.sgb.presentation.services.CategoriaService;
import org.example.sgb.presentation.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/libros")
public class LibroController {  // Cambiar el nombre a LibroController
    private final LibroService libroService;
    private final CategoriaService categoriaService;
    private final AutorService autorService;

    @Autowired
    public LibroController(LibroService libroService, CategoriaService categoriaService, AutorService autorService) {
        this.libroService = libroService;
        this.categoriaService = categoriaService;
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<?> guardarLibro(@RequestBody Libro libro) {
        try {
            Libro libroGuardado = libroService.guardarLibro(libro);
            return ResponseEntity.status(HttpStatus.CREATED).body(libroGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al guardar el libro: " + e.getMessage()));
        }
    }

    @GetMapping("/categoria/{nombreCategoria}")
    public ResponseEntity<?> obtenerLibrosPorCategoria(@PathVariable String nombreCategoria) {
        try {
            List<Libro> libros = libroService.listarLibrosPorCategoria(nombreCategoria);
            if (libros.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "No se encontraron libros para la categoría: " + nombreCategoria));
            }
            return ResponseEntity.ok(libros);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al buscar libros: " + e.getMessage()));
        }
    }

    @GetMapping("/autor/{nombreAutor}")
    public ResponseEntity<?> obtenerLibrosPorAutor(@PathVariable String nombreAutor) {

        try {
            List<Libro> libros = libroService.listarLibrosPorAutor(nombreAutor);
            if (libros.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "No se encontraron libros por el autor: " + nombreAutor));
            }
            return ResponseEntity.ok(libros);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al buscar libros: " + e.getMessage()));
        }
    }

    @GetMapping("/libroPrestado/{nombreCliente}")
    public ResponseEntity<?> obtenerLibrosPrestadoPorCliente(@PathVariable String nombreCliente) {

        try {
            List<Libro> libros = libroService.listarLibrosPorCliente(nombreCliente);
            if (libros.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "No se encontraron libros prestador por: " + nombreCliente));
            }
            return ResponseEntity.ok(libros);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al buscar libros: " + e.getMessage()));
        }
    }

    @GetMapping("libro/{tituloLibro}")
    public ResponseEntity<?> obtenerLibros(@PathVariable String tituloLibro){
        try {
            List<Libro> libros = libroService.listarLibros(tituloLibro);
            if (libros.isEmpty()) {
                // Si no se encuentra el libro, buscar en Google Books API
                LibroDTO booksFromApi = libroService.buscarEnGoogleBooksAPI(tituloLibro);
                if (booksFromApi ==null) {
                    return ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(Map.of("mensaje", "No se encontraron libros con el título: " + tituloLibro));
                }
                return ResponseEntity.ok(booksFromApi);
            }
            return ResponseEntity.ok(libros);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al buscar libros: " + e.getMessage()));
        }
    }

    @PutMapping("/libro/{id}")
    public ResponseEntity<?> actualizarLibro(@PathVariable("id") Short id, @RequestBody Libro libro) {
        try {
            Libro libroActualizado = libroService.actualizarLibro(id, libro);
            return ResponseEntity.ok(libroActualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Libro con ID " + id + " no encontrado"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al actualizar el libro: " + e.getMessage()));
        }
    }

    @DeleteMapping("/eliminarLibro/{id}")
    public ResponseEntity<?> EliminarLibro(@PathVariable("id") Short id, @RequestBody Libro libro) {
        try {
            Libro libroEliminado = libroService.eliminarLibro(id, libro);
            return ResponseEntity.ok(libroEliminado);
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Libro con ID " + id + " no encontrado"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al eliminar el libro: " + e.getMessage()));
        }
    }
}
