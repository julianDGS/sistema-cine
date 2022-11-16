package com.jg.controller;

import com.jg.dto.PersonajeRequestDto;
import com.jg.dto.PersonajeDto;
import com.jg.dto.RodajeDto;
import com.jg.exceptions.InvalidFieldException;
import com.jg.service.CatalogoService;
import com.jg.service.ICharacterService;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/characters")
@CrossOrigin
public class CharacterController {

    private final CatalogoService catalogoService;

    private final ICharacterService personajeService;

    @Autowired
    public CharacterController(CatalogoService catalogoService, ICharacterService personajeService) {
        this.catalogoService = catalogoService;
        this.personajeService = personajeService;
    }

    @GetMapping("/img/{idPersonaje}")
    private ResponseEntity<Resource> cargarImagen(@PathVariable("idPersonaje") long idPersonaje) {
        return new ResponseEntity<>(catalogoService.cargarArchivo(idPersonaje), HttpStatus.OK);
    }

    @GetMapping()
    private ResponseEntity<List<PersonajeDto>> listadoPersonajes() {
        return new ResponseEntity<>(personajeService.listarPersonajes(), HttpStatus.OK);
    }

    @GetMapping("/save")
    private ResponseEntity<List<RodajeDto>> formularioCrearPersonaje() {
        return new ResponseEntity<>(personajeService.listarRodajes(), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<PersonajeDto> guardarPersonaje(@Valid @RequestBody PersonajeRequestDto personaje, BindingResult br) {
        if (br.hasErrors()) {
            throw new InvalidFieldException("Campos no válidos", br);
        }
        return new ResponseEntity<>(personajeService.guardar(personaje), HttpStatus.OK);
    }

    @GetMapping("/update/{idPersonaje}")
    private ResponseEntity<PersonajeDto> editarPersonaje(@PathVariable long idPersonaje) {
        return new ResponseEntity<>(personajeService.encontrar(idPersonaje), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idPersonaje}")
    private ResponseEntity<String> eliminarPersonaje(@PathVariable("idPersonaje") long idPersonaje) {
        personajeService.eliminar(idPersonaje);
        return new ResponseEntity<>("Character deleted", HttpStatus.OK);
    }

    @GetMapping("/details/{idPersonaje}")
    private ResponseEntity<PersonajeDto> detallesPersonaje(@PathVariable long idPersonaje) {
        return new ResponseEntity<>(personajeService.encontrar(idPersonaje), HttpStatus.OK);
    }

    @GetMapping(params = "name")
    private ResponseEntity<PersonajeDto> buscarPersonaje(@RequestParam(name = "name") String nombre) {
        return new ResponseEntity<>(personajeService.encontrarPorNombre(nombre), HttpStatus.OK);
    }

    @GetMapping(params = "age")
    private ResponseEntity<List<PersonajeDto>> filtrarEdadPersonaje(@RequestParam(name = "age") int edad) {
        return new ResponseEntity<>(personajeService.filtrarPorEdad(edad), HttpStatus.OK);
    }

    @GetMapping(params = "movies")
    private ResponseEntity<List<PersonajeDto>> filtrarRodajePersonaje(@RequestParam(name = "movies") long idMovie) {
        return new ResponseEntity<>(personajeService.filtrarRodajePersonaje(idMovie), HttpStatus.OK);
    }
}
