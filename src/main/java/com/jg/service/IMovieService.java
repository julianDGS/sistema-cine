package com.jg.service;

import com.jg.dto.GeneroDto;
import com.jg.dto.RodajeDto;
import com.jg.dto.RodajeRequestDto;

import java.util.List;
import java.util.Optional;

public interface IMovieService {
    List<RodajeDto> listarRodajes();

    List<GeneroDto> listarGeneros();

    RodajeDto guardar(RodajeRequestDto rodaje);

    RodajeDto encontrar(Long idRodaje);

    void eliminar(long idRodaje);

    RodajeDto encontrarPorTitulo(String titulo);

    List<RodajeDto> filtrarGeneroRodaje(long idGenero);

    List<RodajeDto> ordenarRodajeFecha(String sort);
}
