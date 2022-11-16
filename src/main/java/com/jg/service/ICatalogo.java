package com.jg.service;

import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface ICatalogo {
    
    String guardarArchivo(MultipartFile multipartFile);
    Resource cargarArchivo(long idPersonaje);
    Resource cargarArchivoRodaje(long idRodaje);
    void eliminarArchivo(String nombreArchivo);
}
