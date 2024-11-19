package org.example.sgb.presentation.services;

import org.example.sgb.persistence.entity.Categoria;
import org.example.sgb.persistence.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria buscarCategoriaPorNombre(String nombreCategoria) {
        return categoriaRepository.findByNombreCategoria(nombreCategoria);
    }

    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
