package com.jg.controller;

import com.jg.domain.*;
import com.jg.service.Catalogo;
import com.jg.service.PersonajeService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/characters")
@CrossOrigin
public class CharacterController {

    @Autowired
    private Catalogo catalogo;

    @Autowired
    private PersonajeService personajeService;

    @GetMapping("/img/{idPersonaje}")
    private ResponseEntity<?> cargarImagen(@PathVariable("idPersonaje") long idPersonaje) {
        Personaje personaje = personajeService.encontrar(idPersonaje).orElse(null);
        return new ResponseEntity(catalogo.cargarArchivo(personaje.getImagen()), HttpStatus.OK);
    }

    @GetMapping()
    private ResponseEntity<Personaje> listadoPersonajes() {
        List<Personaje> personajes = personajeService.listarPersonajes();
        return new ResponseEntity(personajes, HttpStatus.OK);
    }

    @GetMapping("/save")
    private ResponseEntity<?> formularioCrearPersonaje() {
        List<Rodaje> rodajes = personajeService.listarRodajes();
        return new ResponseEntity(rodajes, HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<?> guardarPersonaje(@Valid @RequestBody Personaje personaje, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity("Fail creating character", HttpStatus.BAD_REQUEST);
        }
        personaje.setImagen(catalogo.guardarArchivo(personaje.getImgTemp()));
        return new ResponseEntity(personajeService.guardar(personaje), HttpStatus.OK);
    }

    @GetMapping("/update/{idPersonaje}")
    private ResponseEntity<?> editarPersonaje(Personaje personaje) {
        return new ResponseEntity(personajeService.encontrar(personaje.getIdPersonaje()).orElse(null), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idPersonaje}")
    private ResponseEntity<?> eliminarPersonaje(@PathVariable("idPersonaje") long idPersonaje) {
        Personaje personaje = personajeService.encontrar(idPersonaje).orElse(null);
        catalogo.eliminarArchivo(personaje.getImagen());
        personajeService.eliminar(personaje);
        return new ResponseEntity("Character deleted", HttpStatus.OK);
    }

    @GetMapping("/details/{idPersonaje}")
    private ResponseEntity<?> detallesPersonaje(Personaje personaje) {
        personaje = personajeService.encontrar(personaje.getIdPersonaje()).orElse(null);
        if (personaje == null) {
            return new ResponseEntity("Character doesn't exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(personaje, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    private ResponseEntity<?> buscarPersonaje(@RequestParam(name = "name") String nombre) {
        return new ResponseEntity(personajeService.encontrarPorNombre(nombre), HttpStatus.OK);
    }

    @GetMapping(params = "age")
    private ResponseEntity<?> filtrarEdadPersonaje(@RequestParam(name = "age") int edad) {
        List<Personaje> personajes = personajeService.listarPersonajes();
        personajes = personajes.stream()
                .filter(i -> i.getEdad() == edad)
                .collect(Collectors.toList());
        return new ResponseEntity(personajes, HttpStatus.OK);
    }

    @GetMapping(params = "movies")
    private ResponseEntity<?> filtrarRodajePersonaje(@RequestParam(name = "movies") long idMovie) {
        List<Personaje> personajes = (List<Personaje>) this.listadoPersonajes().getBody();
        personajes = personajes.stream()
                .filter(i -> i.getRodajes() == null ? false : i.getRodajes().stream()
                .anyMatch(j -> j.getIdRodaje() == idMovie))
                .collect(Collectors.toList());
        return new ResponseEntity(personajes, HttpStatus.OK);
    }
}
