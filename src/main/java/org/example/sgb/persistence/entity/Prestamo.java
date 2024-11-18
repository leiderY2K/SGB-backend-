package org.example.sgb.persistence.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Prestamo implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short idprestamo;

    @ManyToOne
    @JoinColumn(name = "idclientefk")
    private Cliente cliente;

    @Column(name = "fechainicioprestamo", insertable = true, updatable = false)
    @CreationTimestamp
    private LocalDateTime fechainicioprestamo;

    private Date fechafinprestamo;
}
