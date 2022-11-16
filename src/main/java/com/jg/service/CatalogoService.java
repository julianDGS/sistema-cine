package com.jg.service;

import com.jg.dto.PersonajeDto;
import com.jg.dto.RodajeDto;
import com.jg.exceptions.CatalogoException;
import com.jg.exceptions.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CatalogoService implements ICatalogo {

    @Value("${storage.location}")
    private String storage;
    private final PersonajeService personajeService;
    private final RodajeService rodajeService;

    @Autowired
    public CatalogoService(PersonajeService personajeService, RodajeService rodajeService) {
        this.personajeService = personajeService;
        this.rodajeService = rodajeService;
    }

    @Override
    @Transactional
    public String guardarArchivo(MultipartFile archivo) {
        Path uploadPath = Paths.get(storage);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException ex) {
                throw new CatalogoException("Error initializing directory");
            }
        }
        if (archivo == null) {
            return null;
        }
        String nombreArchivo = archivo.getOriginalFilename();
        try {
            InputStream is = archivo.getInputStream();
            Path ruta = uploadPath.resolve(nombreArchivo);
            Files.copy(is, ruta, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new CatalogoException("Could not save image file: " + nombreArchivo);
        }
        return nombreArchivo;
    }

    @Override
    @Transactional(readOnly = true)
    public Resource cargarArchivo(long idPersonaje) {
        PersonajeDto personaje = personajeService.encontrar(idPersonaje);
        String nombreArchivo = personaje.getImagen();
        return archivo(nombreArchivo);
    }

    @Override
    public Resource cargarArchivoRodaje(long idRodaje) {
        RodajeDto rodaje = rodajeService.encontrar(idRodaje);
        String nombreArchivo = rodaje.getImagen();
        return archivo(nombreArchivo);

    }

    private Resource archivo(String nombreArchivo){
        Path archivo = Paths.get(storage).resolve(nombreArchivo);
        try {
            Resource recurso = new UrlResource(archivo.toUri());
            if (recurso.exists() || recurso.isReadable()) {
                return recurso;
            } else {
                throw new FileNotFoundException("No se pudo encontrar el archivo " + nombreArchivo);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("No se pudo encontrar el archivo " + nombreArchivo);
        }
    }

    @Override
    @Transactional
    public void eliminarArchivo(String nombreArchivo) {
        if (nombreArchivo != null) {
            Path archivo = Paths.get(storage).resolve(nombreArchivo);
            try {
                FileSystemUtils.deleteRecursively(archivo);
            } catch (IOException excepcion) {
                System.out.println(excepcion);
            }
        }
    }
}
