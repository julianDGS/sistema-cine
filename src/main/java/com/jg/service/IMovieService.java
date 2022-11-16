package com.jg.service;

import com.jg.domain.Genero;
import com.jg.domain.Rodaje;
import java.util.List;
import java.util.Optional;

public interface IMovieService {
     List<Rodaje> listarRodajes();

     List<Genero> listarGeneros();

     Rodaje guardar(Rodaje rodaje);

    Rodaje encontrar(Long idRodaje);
    
    void eliminar(long idRodaje);
    
    Rodaje encontrarPorTitulo(String titulo);

    List<Rodaje> filtrarGeneroRodaje(long idGenero);

    List<Rodaje> ordenarRodajeFecha(String sort);
}
