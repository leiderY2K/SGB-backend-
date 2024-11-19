package org.example.sgb.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategoria")
    private Short idCategoria;

    @Column(name = "nombrecategoria")
    private String nombreCategoria;

    @Column(name = "descripcion")
    private String descripcion;
}
