package com.jg.controller;

import com.jg.domain.*;
import com.jg.exceptions.InvalidFieldException;
import com.jg.service.CatalogoService;
import com.jg.service.ICharacterService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.aspectj.weaver.patterns.PerObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/characters")
@CrossOrigin
public class CharacterController {

    @Autowired
    private CatalogoService catalogoService;

    @Autowired
    private ICharacterService personajeService;

    @GetMapping("/img/{idPersonaje}")
    private ResponseEntity<Resource> cargarImagen(@PathVariable("idPersonaje") long idPersonaje) {
        return new ResponseEntity<>(catalogoService.cargarArchivo(idPersonaje), HttpStatus.OK);
    }

    @GetMapping()
    private ResponseEntity<List<Personaje>> listadoPersonajes() {
        return new ResponseEntity<>(personajeService.listarPersonajes(), HttpStatus.OK);
    }

    @GetMapping("/save")
    private ResponseEntity<List<Rodaje>> formularioCrearPersonaje() {
        return new ResponseEntity<>(personajeService.listarRodajes(), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<Personaje> guardarPersonaje(@Valid @RequestBody Personaje personaje, BindingResult br) {
        if (br.hasErrors()) {
            throw new InvalidFieldException("Campos no válidos", br);
        }
        return new ResponseEntity<>(personajeService.guardar(personaje), HttpStatus.OK);
    }

    @GetMapping("/update/{idPersonaje}")
    private ResponseEntity<Personaje> editarPersonaje(Personaje personaje) {
        return new ResponseEntity<>(personajeService.encontrar(personaje.getIdPersonaje()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idPersonaje}")
    private ResponseEntity<String> eliminarPersonaje(@PathVariable("idPersonaje") long idPersonaje) {
        personajeService.eliminar(idPersonaje);
        return new ResponseEntity<>("Character deleted", HttpStatus.OK);
    }

    @GetMapping("/details/{idPersonaje}")
    private ResponseEntity<Personaje> detallesPersonaje(Personaje personaje) {
        return new ResponseEntity<>(personajeService.encontrar(personaje.getIdPersonaje()), HttpStatus.OK);
    }

    @GetMapping(params = "name")
    private ResponseEntity<Personaje> buscarPersonaje(@RequestParam(name = "name") String nombre) {
        return new ResponseEntity<>(personajeService.encontrarPorNombre(nombre), HttpStatus.OK);
    }

    @GetMapping(params = "age")
    private ResponseEntity<List<Personaje>> filtrarEdadPersonaje(@RequestParam(name = "age") int edad) {
        return new ResponseEntity<>(personajeService.filtrarPorEdad(edad), HttpStatus.OK);
    }

    @GetMapping(params = "movies")
    private ResponseEntity<List<Personaje>> filtrarRodajePersonaje(@RequestParam(name = "movies") long idMovie) {
        return new ResponseEntity<>(personajeService.filtrarRodajePersonaje(idMovie), HttpStatus.OK);
    }
}
