package com.jg.service;

import com.jg.domain.Genero;
import com.jg.domain.Rodaje;
import java.util.List;
import java.util.Optional;

public interface IMovieService {
    public  List<Rodaje> listarRodajes();
    
    public List<Genero> listarGeneros();
    
    public Rodaje guardar(Rodaje rodaje);
    
    public Optional<Rodaje> encontrar(Long idRodaje);
    
    public void eliminar(Rodaje rodaje);
    
    public Rodaje encontrarPorTitulo(String titulo);
}
