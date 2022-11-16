package com.jg.service;

import com.jg.domain.Rodaje;
import com.jg.dto.GeneroDto;
import com.jg.dto.RodajeDto;
import com.jg.dto.RodajeRequestDto;
import com.jg.exceptions.CustomNotFoundException;
import com.jg.mapper.GeneroMapper;
import com.jg.mapper.RodajeMapper;
import com.jg.repository.GeneroRepository;
import com.jg.repository.RodajeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RodajeService implements IMovieService {

    private final RodajeRepository rodajeRepo;
    private final GeneroRepository generoRepo;
    private final ICatalogo catalogoService;
    private final RodajeMapper rodajeMapper;
    private final GeneroMapper generoMapper;

    @Autowired
    public RodajeService(RodajeRepository rodajeRepo, GeneroRepository generoRepo, ICatalogo catalogoService, RodajeMapper rodajeMapper, GeneroMapper generoMapper) {
        this.rodajeRepo = rodajeRepo;
        this.generoRepo = generoRepo;
        this.catalogoService = catalogoService;
        this.rodajeMapper = rodajeMapper;
        this.generoMapper = generoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RodajeDto> listarRodajes() {
        return rodajeMapper.rodajesToRodajeDtos(rodajeRepo.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GeneroDto> listarGeneros() {
        return generoMapper.generosToGeneroDtos(generoRepo.findAll());
    }

    @Override
    @Transactional
    public RodajeDto guardar(RodajeRequestDto rodajeRequestDto) {
        Rodaje rodaje = rodajeMapper.rodajeRequestDtoToRodaje(rodajeRequestDto);
        rodaje.setImagen(catalogoService.guardarArchivo(rodajeRequestDto.getImgTemp()));
        return rodajeMapper.rodajeToRodajeDto(rodajeRepo.save(rodaje));
    }

    @Override
    @Transactional(readOnly = true)
    public RodajeDto encontrar(Long idRodaje) {
        return rodajeMapper.rodajeToRodajeDto(rodajeRepo.findById(idRodaje).orElseThrow(()->{
            throw new CustomNotFoundException("Rodaje no encontrado");
        }));
    }

    @Override
    @Transactional
    public void eliminar(long idRodaje) {
        Rodaje rodaje = rodajeRepo.findById(idRodaje).orElseThrow(()->{
            throw new CustomNotFoundException("Rodaje no encontrado");
        });
        catalogoService.eliminarArchivo(rodaje.getImagen());
        rodajeRepo.delete(rodaje);
    }

    @Override
    @Transactional(readOnly = true)
    public RodajeDto encontrarPorTitulo(String titulo) {
       return rodajeMapper.rodajeToRodajeDto(rodajeRepo.findByTitulo(titulo));
    }

    @Override
    public List<RodajeDto> filtrarGeneroRodaje(long idGenero) {
        return listarRodajes()
                .stream()
                .filter(i -> i.getGeneros() != null && i.getGeneros().stream()
                        .anyMatch(j -> j.getIdGenero() == idGenero))
                .collect(Collectors.toList());
    }

    @Override
    public List<RodajeDto> ordenarRodajeFecha(String sort) {
        List<RodajeDto> rodajes = listarRodajes();
        if(sort.equals("ASC")){
            rodajes.sort(Comparator.comparing(RodajeDto::getFecha));
        } else if (sort.equals("DESC")){
            rodajes.sort(Comparator.comparing(RodajeDto::getFecha).reversed());
        }
        return rodajes;
    }

}
