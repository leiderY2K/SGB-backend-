package org.example.sgb.presentation.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.sgb.models.LibroDTO;
import org.example.sgb.persistence.entity.Autor;
import org.example.sgb.persistence.entity.Categoria;
import org.example.sgb.persistence.entity.Libro;
import org.example.sgb.persistence.repository.AutorRepository;
import org.example.sgb.persistence.repository.CategoriaRepository;
import org.example.sgb.persistence.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LibroService {

    private final LibroRepository libroRepo;
    private final AutorRepository autorRepo;
    private final CategoriaRepository categoriaRepo;
    private HttpClient client;


    private final String API_KEY = "AIzaSyA9-ZYIH7VULwczmYQ7O6SrqSXiDp7o6TE";
    private final String GOOGLE_BOOKS_API = "https://www.googleapis.com/books/v1/volumes";

    @Autowired
    public LibroService(LibroRepository libroRepo, AutorRepository autorRepo, CategoriaRepository categoriaRepo, HttpClient client) {
        this.libroRepo = libroRepo;
        this.autorRepo = autorRepo;
        this.categoriaRepo = categoriaRepo;
        this.client = client;
    }

    @Transactional
    public Libro guardarLibro(Libro libro) {
        // Verificar si el autor ya existe por su nombre
        Autor autorExistente = autorRepo.findByNombre(libro.getAutor().getNombre());

        if (autorExistente == null) {
            // Crear nuevo autor si no existe
            Autor nuevoAutor = new Autor();
            nuevoAutor.setNombre(libro.getAutor().getNombre());
            nuevoAutor.setPaisOrigen(libro.getAutor().getPaisOrigen());
            autorExistente = autorRepo.save(nuevoAutor);
        }

        // Asociar el autor existente o creado al libro
        libro.setAutor(autorExistente);

        // Guardar el libro
        return libroRepo.save(libro);
    }


    public List<Libro> listarLibrosPorCategoria(String nombreCategoria) {
        return libroRepo.findByCategoria(nombreCategoria);
    }

    public List<Libro> listarLibrosPorAutor(String nombreAutor) {
        String nombreAutorC = "%" + nombreAutor + "%";
        return libroRepo.findByAutor(nombreAutorC);
    }

    public List<Libro> listarLibrosPorCliente(String nombreCliente) {
        String nombreClienteC = "%" + nombreCliente + "%";
        return libroRepo.findByCliente(nombreClienteC);
    }

    public List<Libro> listarLibros(String tituloLibro) {
        String tituloLibroC = "%" + tituloLibro + "%";
        return libroRepo.findByTitulo(tituloLibroC);

    }

    public LibroDTO buscarEnGoogleBooksAPI(String titulo) {
        try {
            // Codificar el título para que sea seguro para la URL
            String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8.toString());

            // Construir la URL con el título codificado
            URI uri = new URI(GOOGLE_BOOKS_API + "?q=" + tituloCodificado + "&key=" + API_KEY);

            HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());
            JsonNode items = root.get("items");

            // Comprobar si se encontró algún libro
            if (items != null && items.size() > 0) {
                JsonNode volumeInfo = items.get(0).get("volumeInfo");

                // Obtener el primer autor si está disponible
                JsonNode authors = volumeInfo.get("authors");
                String autor = (authors != null && authors.size() > 0) ? authors.get(0).asText() : "Autor desconocido";

                // Obtener el título del libro
                String tituloLibro = volumeInfo.has("title") ? volumeInfo.get("title").asText() : "Sin título";

                // Crear y devolver el DTO con el primer libro
                return new LibroDTO(
                        tituloLibro,
                        volumeInfo.has("publishedDate") ? volumeInfo.get("publishedDate").asText() : "Desconocida",
                        volumeInfo.has("description") ? volumeInfo.get("description").asText() : null,
                        autor
                );
            }

            // Si no se encuentra ningún libro, devolver null o un valor por defecto
            return null;

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar en Google Books API: " + e.getMessage(), e);
        }
    }

    @Transactional
    public Libro actualizarLibro(Short id, Libro libro) {
        // Buscar el libro existente
        Libro libroExistente = libroRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Libro no encontrado"));


        libroExistente.setTitulo(libro.getTitulo());
        libroExistente.setAñoPublicacion(libro.getAñoPublicacion());
        libroExistente.setDisponibilidad(libro.isDisponibilidad());
        libroExistente.setDescripcion(libro.getDescripcion());

        if (libro.getCategoria() != null && libro.getCategoria().getIdCategoria() != null) {
            Categoria categoria = categoriaRepo.findById(libro.getCategoria().getIdCategoria())
                    .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada"));
            libroExistente.setCategoria(categoria);
        }

        if (libro.getAutor() != null && libro.getAutor().getIdAutor() != null) {
            Autor autor = autorRepo.findById(libro.getAutor().getIdAutor())
                    .orElseThrow(() -> new NoSuchElementException("Autor no encontrado"));
            libroExistente.setAutor(autor);
        }

        return libroRepo.save(libroExistente);
    }

    @Transactional
    public Libro eliminarLibro(Short id, Libro libro) {
        Libro libroExistente = libroRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Libro no encontrado"));

        libroRepo.delete(libroExistente);
        return libroExistente;
    }
}
