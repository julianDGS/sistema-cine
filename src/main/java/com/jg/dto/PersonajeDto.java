package com.jg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
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
