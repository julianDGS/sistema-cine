package com.jg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RodajeRequestDto {
    private long idRodaje;
    @NotEmpty(message = "titulo no puede estar vacío")
    private String titulo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    @NotNull(message = "calificacion no puede ser nula")
    private Integer calificacion;
    @Transient
    private MultipartFile imgTemp;
    private final Set<GeneroRequestDto> generos = new HashSet<>();
}
