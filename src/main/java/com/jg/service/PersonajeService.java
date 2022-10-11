package com.jg.service;

import com.jg.domain.Personaje;
import com.jg.domain.Rodaje;
import com.jg.repository.PersonajeRepository;
import com.jg.repository.RodajeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonajeService implements ICharacterService{
    
    @Autowired
    private PersonajeRepository personajeRepo;

    @Autowired
    private RodajeRepository rodajeRepo;
    
    @Override
    @Transactional(readOnly = true)
    public List<Personaje> listarPersonajes() {
        return personajeRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rodaje> listarRodajes() {
        return rodajeRepo.findAll();
    }

    @Override
    @Transactional
    public Personaje guardar(Personaje rodaje) {
        return personajeRepo.save(rodaje);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Personaje> encontrar(Long idPersonaje) {
        return personajeRepo.findById(idPersonaje);
    }

    @Override
    @Transactional
    public void eliminar(Personaje rodaje) {
        personajeRepo.delete(rodaje);
    }

    @Override
    @Transactional(readOnly = true)
    public Personaje encontrarPorNombre(String nombre) {
       return personajeRepo.findByNombre(nombre);
    }
    
}
