package org.example.sgb.presentation.controller;

import org.example.sgb.persistence.entity.Libro;
import org.example.sgb.presentation.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/libros")
public class LibroController {  // Cambiar el nombre a LibroController
    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro){
        return libroService.crearLibro(libro);
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

    @GetMapping("/nombre/{nombreAutor}")
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
}
