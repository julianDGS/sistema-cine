package com.jg.security.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Table(name = "usuario")
@NoArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    @NotBlank
    @Column(unique = true)
    private String username;
    
    @NotBlank
    private String password;
    
    @Email
    @Column(unique = true)
    private String email;
    
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="usuarios_roles",
            joinColumns = @JoinColumn(name="id_usuario"),
            inverseJoinColumns = @JoinColumn(name="id_rol")
    )
    private Set<Rol> roles = new HashSet<>();

    public Usuario(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
