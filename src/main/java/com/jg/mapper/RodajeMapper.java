package com.jg.mapper;

import com.jg.domain.Rodaje;
import com.jg.dto.RodajeRequestDto;
import com.jg.dto.RodajeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RodajeMapper {
    
    RodajeDto rodajeToRodajeDto(Rodaje rodaje);
    List<RodajeDto> rodajesToRodajeDtos(List<Rodaje> rodaje);
    Rodaje rodajeRequestDtoToRodaje(RodajeRequestDto rodaje);
}
