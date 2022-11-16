package com.jg.service;

import com.jg.domain.Personaje;
import com.jg.dto.PersonajeRequestDto;
import com.jg.dto.PersonajeDto;
import com.jg.dto.RodajeDto;
import com.jg.exceptions.CustomNotFoundException;
import com.jg.mapper.PersonajeMapper;
import com.jg.mapper.RodajeMapper;
import com.jg.repository.PersonajeRepository;
import com.jg.repository.RodajeRepository;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonajeService implements ICharacterService{
    
    private final PersonajeRepository personajeRepo;

    private final RodajeRepository rodajeRepo;

    private final ICatalogo catalogo;

    private final PersonajeMapper personajeMapper;

    private final RodajeMapper rodajeMapper;

    @Autowired
    public PersonajeService(PersonajeRepository personajeRepo, RodajeRepository rodajeRepo, ICatalogo catalogo, PersonajeMapper personajeMapper, RodajeMapper rodajeMapper) {
        this.personajeRepo = personajeRepo;
        this.rodajeRepo = rodajeRepo;
        this.catalogo = catalogo;
        this.personajeMapper = personajeMapper;
        this.rodajeMapper = rodajeMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonajeDto> listarPersonajes() {
        return personajeMapper.personajesToPersonajeResponseDtos(personajeRepo.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RodajeDto> listarRodajes() {
        return rodajeMapper.rodajesToRodajeResponseDtos(rodajeRepo.findAll());
    }

    @Override
    @Transactional
    public PersonajeDto guardar(PersonajeRequestDto personajeRequestDto) {
        Personaje personaje = personajeMapper.personajeRequestDtoToPersonaje(personajeRequestDto);
        personaje.setImagen(catalogo.guardarArchivo(personajeRequestDto.getImgTemp()));
        return personajeMapper.personajeToPersonajeResponseDto(personajeRepo.save(personaje));
    }

    @Override
    @Transactional(readOnly = true)
    public PersonajeDto encontrar(Long idPersonaje) {
        return personajeMapper.personajeToPersonajeResponseDto(personajeRepo.findById(idPersonaje).orElseThrow(() -> {
            throw new CustomNotFoundException("Personaje no encontrado");
        }));
    }

    @Override
    @Transactional
    public void eliminar(long idPersonaje) {
        Personaje personaje = personajeRepo.findById(idPersonaje).orElseThrow(() -> {
            throw new CustomNotFoundException("Personaje no encontrado");
        });
        catalogo.eliminarArchivo(personaje.getImagen());
        personajeRepo.delete(personaje);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonajeDto encontrarPorNombre(String nombre) {
       return personajeMapper.personajeToPersonajeResponseDto(personajeRepo.findByNombre(nombre));
    }

    @Override
    public List<PersonajeDto> filtrarPorEdad(int edad) {
        return listarPersonajes()
                .stream()
                .filter(i -> i.getEdad() == edad)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonajeDto> filtrarRodajePersonaje(long idMovie) {
        return listarPersonajes()
                .stream()
                .filter(
                        i -> i.getRodajes() != null && i.getRodajes()
                        .stream()
                        .anyMatch(j -> j.getIdRodaje() == idMovie)
                        )
                .collect(Collectors.toList());
    }

}
