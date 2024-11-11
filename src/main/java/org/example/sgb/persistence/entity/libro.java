package org.example.sgb.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class libro implements Serializable {
    @Id
    private short idlibro;

    private String titulo;

    private String añopublicacion;

    private boolean disponibilidad;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idcategoriafk")
    private categoria idCategoria;

    @ManyToOne
    @JoinColumn(name = "idautorfk")
    private autor idAutor;
}
