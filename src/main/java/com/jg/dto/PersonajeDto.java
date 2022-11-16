package com.jg.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonajeDto {

    private long idPersonaje;
    private String nombre;
    private int edad;
    private float peso;
    private String historia;
    private String imagen;
    private final Set<RodajeDto> rodajes = new HashSet<>();

}
