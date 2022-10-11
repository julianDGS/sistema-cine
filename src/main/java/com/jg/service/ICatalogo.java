package com.jg.service;

import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ICatalogo {
    
    public String guardarArchivo(MultipartFile multipartFile);
    
    public Resource cargarArchivo(String nombreArchivo);
    
    public void eliminarArchivo(String nombreArchivo);
}
