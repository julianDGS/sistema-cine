package com.jg.mapper;

import com.jg.domain.Personaje;
import com.jg.dto.PersonajeRequestDto;
import com.jg.dto.PersonajeResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonajeMapper {

    PersonajeResponseDto personajeToPersonajeResponseDto(Personaje personaje);
    List<PersonajeResponseDto> personajesToPersonajeResponseDtos(List<Personaje> personaje);
    Personaje personajeRequestDtoToPersonaje(PersonajeRequestDto personaje);

}
