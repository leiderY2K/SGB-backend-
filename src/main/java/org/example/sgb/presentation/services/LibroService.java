package org.example.sgb.presentation.services;

import org.example.sgb.persistence.entity.libro;
import org.example.sgb.persistence.repository.LibroRepository;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    private final LibroRepository libroRepo;


    public LibroService(LibroRepository libroRepo) {
        this.libroRepo = libroRepo;
    }

    public libro crearLibro(libro libro) {
        return libroRepo.save(libro);
    }
}
