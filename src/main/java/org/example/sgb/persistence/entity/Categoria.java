package org.example.sgb.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "categoria")  // en minúsculas para PostgreSQL
public class Categoria {
    @Id
    @Column(name = "idcategoria")  // en minúsculas para PostgreSQL
    private Short idCategoria;

    @Column(name = "nombrecategoria")  // en minúsculas para PostgreSQL
    private String nombreCategoria;

    @Column(name = "descripcion")
    private String descripcion;
}
