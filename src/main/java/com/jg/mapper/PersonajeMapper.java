package com.jg.mapper;

import com.jg.domain.Personaje;
import com.jg.dto.PersonajeRequestDto;
import com.jg.dto.PersonajeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonajeMapper {

    PersonajeDto personajeToPersonajeResponseDto(Personaje personaje);
    List<PersonajeDto> personajesToPersonajeResponseDtos(List<Personaje> personaje);
    Personaje personajeRequestDtoToPersonaje(PersonajeRequestDto personaje);

}
