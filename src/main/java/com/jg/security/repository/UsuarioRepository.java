package com.jg.security.repository;

import com.jg.security.domain.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Optional<Usuario> findByUsername(String username);
    
    public boolean existsByUsername(String username);
    
    public boolean existsByEmail(String email);
}
