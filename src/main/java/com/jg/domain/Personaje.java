package com.jg.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Data
@Table(name = "personaje")
public class Personaje implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personaje", nullable = false)
    private long idPersonaje;

    private String nombre;

    private int edad;

    private float peso;

    private String historia;
    
    private String imagen;

    @Transient
    private MultipartFile imgTemp;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="personaje_rodaje",
            joinColumns = @JoinColumn(name="id_personaje"),
            inverseJoinColumns = @JoinColumn(name="id_rodaje")
    )
    private Set<Rodaje> rodajes = new HashSet<>();
}