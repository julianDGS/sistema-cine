package com.jg.security.domain;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "rol")
public class Rol  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;
    
    @NotNull
    private String nombre;
    
}
