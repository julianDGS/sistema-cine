package com.jg.service;

import com.jg.dto.PersonajeRequestDto;
import com.jg.dto.PersonajeDto;
import com.jg.dto.RodajeDto;

import java.util.List;

public interface ICharacterService {
    List<PersonajeDto> listarPersonajes();
    
    List<RodajeDto> listarRodajes();
    
    PersonajeDto guardar(PersonajeRequestDto rodaje);
    
    PersonajeDto encontrar(Long idPersonaje);
    
    void eliminar(long idPersonaje);
    
    PersonajeDto encontrarPorNombre(String nombre);

    List<PersonajeDto> filtrarPorEdad(int edad);

    List<PersonajeDto> filtrarRodajePersonaje(long idMovie);
}
