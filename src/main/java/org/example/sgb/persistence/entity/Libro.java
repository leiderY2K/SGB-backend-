package org.example.sgb.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "libro")  // en minúsculas para PostgreSQL
public class Libro implements Serializable {
    @Id
    @Column(name = "idlibro")  // en minúsculas para PostgreSQL
    private Short idLibro;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "añopublicacion")  // en minúsculas para PostgreSQL
    private Short añoPublicacion;

    @Column(name = "disponibilidad")
    private boolean disponibilidad;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idcategoriafk", referencedColumnName = "idcategoria")  // en minúsculas
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idautorfk", referencedColumnName = "idautor")  // en minúsculas
    private Autor autor;
}
