package org.example.sgb.presentation.services;

import org.example.sgb.persistence.entity.Autor;
import org.example.sgb.persistence.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor buscarAutorPorNombre(String nombre) {
        return autorRepository.findByNombre(nombre);
    }

    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }
}
