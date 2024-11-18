package org.example.sgb.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Cliente {

    @Id
    private short idcliente;

    private String nombre;

    private String correo;

    private String telefono;

    private boolean estadocuenta;

}
