package com.jg.mapper;

import com.jg.domain.Genero;
import com.jg.dto.GeneroRequestDto;
import com.jg.dto.GeneroDto;

import java.util.List;

public interface GeneroMapper {

    GeneroDto generoToGeneroDto(Genero genero);
    List<GeneroDto> generosToGeneroDtos(List<Genero> genero);
    Genero generoRequestDtoToGenero(GeneroRequestDto genero);
    
}
