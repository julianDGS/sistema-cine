package com.jg.mapper;

import com.jg.domain.Rodaje;
import com.jg.dto.RodajeRequestDto;
import com.jg.dto.RodajeResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RodajeMapper {
    
    RodajeResponseDto rodajeToRodajeResponseDto(Rodaje rodaje);
    List<RodajeResponseDto> rodajesToRodajeResponseDtos(List<Rodaje> rodaje);
    Rodaje rodajeRequestDtoToRodaje(RodajeRequestDto rodaje);
}
