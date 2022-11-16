package com.jg.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@Setter
@Table(name = "personaje")
public class Personaje{
    
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
