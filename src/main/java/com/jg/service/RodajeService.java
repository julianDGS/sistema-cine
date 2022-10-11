/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jg.service;

import com.jg.domain.Genero;
import com.jg.domain.Rodaje;
import com.jg.repository.GeneroRepository;
import com.jg.repository.RodajeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RodajeService implements IMovieService {

    @Autowired
    private RodajeRepository rodajeRepo;

    @Autowired
    private GeneroRepository generoRepo;
   
    @Override
    @Transactional(readOnly = true)
    public List<Rodaje> listarRodajes() {
        return rodajeRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genero> listarGeneros() {
        return generoRepo.findAll();
    }

    @Override
    @Transactional
    public Rodaje guardar(Rodaje rodaje) {
        return rodajeRepo.save(rodaje);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Rodaje> encontrar(Long idRodaje) {
        return rodajeRepo.findById(idRodaje);
    }

    @Override
    @Transactional
    public void eliminar(Rodaje rodaje) {
        rodajeRepo.delete(rodaje);
    }

    @Override
    @Transactional(readOnly = true)
    public Rodaje encontrarPorTitulo(String titulo) {
       return rodajeRepo.findByTitulo(titulo);
    }
    
}
