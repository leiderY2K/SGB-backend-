package org.example.sgb.models;

public record LibroDTO( short idlibro,
                        String titulo,
                        String añopublicacion,
                        boolean disponibilidad,
                        String descripcion) {
}
