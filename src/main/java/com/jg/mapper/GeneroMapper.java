package com.jg.mapper;

import com.jg.domain.Genero;
import com.jg.dto.GeneroRequestDto;
import com.jg.dto.GeneroResponseDto;

import java.util.List;

public interface GeneroMapper {

    GeneroResponseDto generoToGeneroResponseDto(Genero genero);
    List<GeneroResponseDto> generosToGeneroResponseDtos(List<Genero> genero);
    Genero generoRequestDtoToGenero(GeneroRequestDto genero);
    
}
