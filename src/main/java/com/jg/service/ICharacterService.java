package com.jg.service;

import com.jg.domain.Personaje;
import com.jg.domain.Rodaje;
import java.util.List;
import java.util.Optional;

public interface ICharacterService {
    public  List<Personaje> listarPersonajes();
    
    public List<Rodaje> listarRodajes();
    
    public Personaje guardar(Personaje rodaje);
    
    public Optional<Personaje> encontrar(Long idPersonaje);
    
    public void eliminar(Personaje rodaje);
    
    public Personaje encontrarPorNombre(String nombre);
}
