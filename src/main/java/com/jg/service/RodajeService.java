/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jg.service;

import com.jg.domain.Genero;
import com.jg.domain.Rodaje;
import com.jg.exceptions.CustomNotFoundException;
import com.jg.repository.GeneroRepository;
import com.jg.repository.RodajeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RodajeService implements IMovieService {

    @Autowired
    private RodajeRepository rodajeRepo;

    @Autowired
    private GeneroRepository generoRepo;
    @Autowired
    private ICatalogo catalogoService;
   
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
        rodaje.setImagen(catalogoService.guardarArchivo(rodaje.getImgTemp()));
        return rodajeRepo.save(rodaje);
    }

    @Override
    @Transactional(readOnly = true)
    public Rodaje encontrar(Long idRodaje) {
        return rodajeRepo.findById(idRodaje).orElseThrow(()->{
            throw new CustomNotFoundException("Rodaje no encontrado");
        });
    }

    @Override
    @Transactional
    public void eliminar(long idRodaje) {
        Rodaje rodaje = encontrar(idRodaje);
        catalogoService.eliminarArchivo(rodaje.getImagen());
        rodajeRepo.delete(rodaje);
    }

    @Override
    @Transactional(readOnly = true)
    public Rodaje encontrarPorTitulo(String titulo) {
       return rodajeRepo.findByTitulo(titulo);
    }

    @Override
    public List<Rodaje> filtrarGeneroRodaje(long idGenero) {
        return listarRodajes()
                .stream()
                .filter(i -> i.getGeneros() != null && i.getGeneros().stream()
                        .anyMatch(j -> j.getIdGenero() == idGenero))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rodaje> ordenarRodajeFecha(String sort) {
        List<Rodaje> rodajes = listarRodajes();
        if(sort.equals("ASC")){
            rodajes.sort(Comparator.comparing(Rodaje::getFecha));
        } else if (sort.equals("DESC")){
            rodajes.sort(Comparator.comparing(Rodaje::getFecha).reversed());
        }
        return rodajes;
    }

}
