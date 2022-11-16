package com.jg.repository;

import com.jg.domain.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long>{
    
    Personaje findByNombre(String nombre);
}
