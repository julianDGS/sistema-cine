package com.jg.service;

import com.jg.domain.Personaje;
import com.jg.domain.Rodaje;
import java.util.List;
import java.util.Optional;

public interface ICharacterService {
     List<Personaje> listarPersonajes();
    
    List<Rodaje> listarRodajes();
    
    Personaje guardar(Personaje rodaje);
    
    Personaje encontrar(Long idPersonaje);
    
    void eliminar(long idPersonaje);
    
    public Personaje encontrarPorNombre(String nombre);
}
