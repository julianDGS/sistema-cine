package com.jg.repository;

import com.jg.domain.Rodaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RodajeRepository extends JpaRepository<Rodaje, Long> {
    
    public Rodaje findByTitulo(String titulo);
    
}
