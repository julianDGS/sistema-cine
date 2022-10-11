package com.jg.controller;

import com.jg.domain.*;
import com.jg.service.Catalogo;
import com.jg.service.RodajeService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {

    @Autowired
    private Catalogo catalogo;

    @Autowired
    private RodajeService rodajeService;

    @GetMapping("/img/{idRodaje}")
    private ResponseEntity<?> cargarImagen(@PathVariable("idRodaje") long idRodaje) {
        Rodaje rodaje = rodajeService.encontrar(idRodaje).orElse(null);
        return new ResponseEntity(catalogo.cargarArchivo(rodaje.getImagen()), HttpStatus.OK);
    }

    @GetMapping("")
    private ResponseEntity<Rodaje> listadoRodajes() {
        List<Rodaje> rodajes = rodajeService.listarRodajes();
        return new ResponseEntity(rodajes, HttpStatus.OK);
    }

    @GetMapping("/save")
    private ResponseEntity<?> formularioCrearRodaje() {
        List<Genero> generos = rodajeService.listarGeneros();
        return new ResponseEntity(generos, HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<?> guardarRodaje(@Valid @RequestBody Rodaje rodaje, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity("Fail creating Movie/serie", HttpStatus.BAD_REQUEST);
        }
        rodaje.setImagen(catalogo.guardarArchivo(rodaje.getImgTemp()));
        return new ResponseEntity(rodajeService.guardar(rodaje), HttpStatus.OK);
    }

    @GetMapping("/update/{idRodaje}")
    private ResponseEntity<?> editarRodaje(Rodaje rodaje) {
        return new ResponseEntity(rodajeService.encontrar(rodaje.getIdRodaje()).orElse(null), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idRodaje}")
    private ResponseEntity<?> eliminarRodaje(@PathVariable("idRodaje") long idRodaje) {
        Rodaje rodaje = rodajeService.encontrar(idRodaje).orElse(null);
        catalogo.eliminarArchivo(rodaje.getImagen());
        rodajeService.eliminar(rodaje);
        return new ResponseEntity("Movie or Serie deleted", HttpStatus.OK);
    }

    @GetMapping("/details/{idRodaje}")
    private ResponseEntity<?> detallesRodaje(Rodaje rodaje) {
        rodaje = rodajeService.encontrar(rodaje.getIdRodaje()).orElse(null);
        if (rodaje == null) {
            return new ResponseEntity("This Movie or Serie doesn't exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(rodaje, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    private ResponseEntity<?> buscarRodaje(@RequestParam(name = "name") String titulo) {
        return new ResponseEntity(rodajeService.encontrarPorTitulo(titulo), HttpStatus.OK);
    }

    @GetMapping(params = "genre")
    private ResponseEntity<?> filtrarGeneroRodaje(@RequestParam(name = "genre") long idGenero) {
        List<Rodaje> rodajes = rodajeService.listarRodajes();
        rodajes = rodajes.stream()
                .filter(i -> i.getGeneros() == null ? false : i.getGeneros().stream()
                .anyMatch(j -> j.getIdGenero() == idGenero))
                .collect(Collectors.toList());
        return new ResponseEntity(rodajes, HttpStatus.OK);
    }

    @GetMapping(params = "order")
    private ResponseEntity<?> ordenarRodajeFecha(@RequestParam(name = "order") String sort) {
        List<Rodaje> rodajes = (List<Rodaje>) this.listadoRodajes().getBody();
        if (rodajes == null){
            return new ResponseEntity("Movies is empty", HttpStatus.BAD_REQUEST);
        }
        if(sort.equals("ASC")){
            rodajes.sort((o1, o2) -> o1.getFecha().compareTo(o2.getFecha()));
        } else if (sort.equals("DESC")){
            rodajes.sort(Comparator.comparing((Rodaje o) -> o.getFecha()).reversed());
        }
        return new ResponseEntity(rodajes, HttpStatus.OK);
    }
}
