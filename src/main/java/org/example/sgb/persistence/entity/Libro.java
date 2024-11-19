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
@Table(name = "libro")
public class Libro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlibro")
    private Short idLibro;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "añopublicacion")
    private Short añoPublicacion;

    @Column(name = "disponibilidad")
    private boolean disponibilidad;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idcategoriafk", referencedColumnName = "idcategoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idautorfk", referencedColumnName = "idautor")
    private Autor autor;
}
