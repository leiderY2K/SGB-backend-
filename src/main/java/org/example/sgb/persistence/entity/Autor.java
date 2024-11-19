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
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idautor")
    private Short idAutor;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "paisOrigen")
    private String paisOrigen;

}
