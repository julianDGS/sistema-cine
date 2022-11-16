package com.jg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonajeRequestDto {

    private long idPersonaje;
    @NotEmpty(message = "nombre no puede ser vacío")
    private String nombre;
    @NotNull(message = "edad no puede ser nula")
    private Integer edad;
    private float peso;
    @NotEmpty(message = "historia no puede estar vacía")
    private String historia;
    @Transient
    private MultipartFile imgTemp;
    private final Set<RodajeFromPersonajeDto> rodajes = new HashSet<>();
}
