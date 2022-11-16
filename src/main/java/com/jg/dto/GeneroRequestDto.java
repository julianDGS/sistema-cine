package com.jg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneroRequestDto {
    private long idGenero;
    private String nombre;
    @Transient
    private MultipartFile imgTemp;
}
