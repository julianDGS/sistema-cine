package com.jg.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@Setter
@Table(name = "genero")
public class Genero {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero", nullable = false)
    private long idGenero;

    private String nombre;

    private String imagen;

    @Transient
    private MultipartFile imgTemp;

}
