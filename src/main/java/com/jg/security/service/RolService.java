package com.jg.security.service;

import com.jg.security.domain.Rol;
import com.jg.security.repository.RolRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolService {
    
    @Autowired
    private RolRepository rolRepository;
    
    @Transactional(readOnly = true)
    public Optional<Rol> encontrar(String nombre){
        return rolRepository.findByNombre(nombre);
    }
    
    @Transactional
    public Rol guardar(Rol rol){
        return rolRepository.save(rol);
    }
    
    
}
