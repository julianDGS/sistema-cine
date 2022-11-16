package com.jg.controller;

import com.jg.dto.GeneroDto;
import com.jg.dto.RodajeDto;
import com.jg.dto.RodajeRequestDto;
import com.jg.exceptions.InvalidFieldException;
import com.jg.service.CatalogoService;
import com.jg.service.IMovieService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {

    private final CatalogoService catalogoService;

    private final IMovieService rodajeService;

    @Autowired
    public MovieController(CatalogoService catalogoService, IMovieService rodajeService) {
        this.catalogoService = catalogoService;
        this.rodajeService = rodajeService;
    }

    @GetMapping("/img/{idRodaje}")
    private ResponseEntity<Resource> cargarImagen(@PathVariable("idRodaje") long idRodaje) {
        return new ResponseEntity<>(catalogoService.cargarArchivoRodaje(idRodaje), HttpStatus.OK);
    }

    @GetMapping("")
    private ResponseEntity<List<RodajeDto>> listadoRodajes() {
        return new ResponseEntity<>(rodajeService.listarRodajes(), HttpStatus.OK);
    }

    @GetMapping("/save")
    private ResponseEntity<List<GeneroDto>> formularioCrearRodaje() {
        return new ResponseEntity<>(rodajeService.listarGeneros(), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<RodajeDto> guardarRodaje(@Valid @RequestBody RodajeRequestDto rodaje, BindingResult br) {
        if (br.hasErrors()) {
            throw new InvalidFieldException("Campos no válidos", br);
        }
        return new ResponseEntity<>(rodajeService.guardar(rodaje), HttpStatus.OK);
    }

    @GetMapping("/update/{idRodaje}")
    private ResponseEntity<RodajeDto> editarRodaje(@PathVariable long idRodaje) {
        return new ResponseEntity<>(rodajeService.encontrar(idRodaje), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idRodaje}")
    private ResponseEntity<String> eliminarRodaje(@PathVariable("idRodaje") long idRodaje) {
        rodajeService.eliminar(idRodaje);
        return new ResponseEntity<>("Movie or Serie deleted", HttpStatus.OK);
    }

    @GetMapping("/details/{idRodaje}")
    private ResponseEntity<RodajeDto> detallesRodaje(@PathVariable long idRodaje) {
        return new ResponseEntity<>(rodajeService.encontrar(idRodaje), HttpStatus.OK);
    }

    @GetMapping(params = "name")
    private ResponseEntity<RodajeDto> buscarRodaje(@RequestParam(name = "name") String titulo) {
        return new ResponseEntity<>(rodajeService.encontrarPorTitulo(titulo), HttpStatus.OK);
    }

    @GetMapping(params = "genre")
    private ResponseEntity<List<RodajeDto>> filtrarGeneroRodaje(@RequestParam(name = "genre") long idGenero) {
        return new ResponseEntity<>(rodajeService.filtrarGeneroRodaje(idGenero), HttpStatus.OK);
    }

    @GetMapping(params = "order")
    private ResponseEntity<List<RodajeDto>> ordenarRodajeFecha(@RequestParam(name = "order") String sort) {
        return new ResponseEntity<>(rodajeService.ordenarRodajeFecha(sort), HttpStatus.OK);
    }
}
