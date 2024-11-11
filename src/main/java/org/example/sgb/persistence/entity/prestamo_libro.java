package org.example.sgb.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.sgb.persistence.entity.EmbeddedClass.prestamo_libroId;

import java.io.Serializable;

@Data
@Entity
public class prestamo_libro implements Serializable {

    @EmbeddedId
    private prestamo_libroId id;

//    @ManyToOne
//    @JoinColumn(name = "idlibro")
//    private libro libro;
//
//    @ManyToOne
//    @JoinColumn(name = "idprestamo")
//    private Prestamo prestamo;

}
