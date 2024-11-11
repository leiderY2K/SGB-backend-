package org.example.sgb.persistence.entity.EmbeddedClass;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class prestamo_libroId implements Serializable {

    private short idprestamo;

    private short idlibro;
}
