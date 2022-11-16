package com.jg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonajeRequestDto {

    private long idPersonaje;
    private String nombre;
    private int edad;
    private float peso;
    private String historia;
    @Transient
    private MultipartFile imgTemp;
    private final Set<RodajeRequestDto> rodajes = new HashSet<>();
}
