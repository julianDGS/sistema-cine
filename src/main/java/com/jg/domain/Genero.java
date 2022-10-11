package com.jg.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Data
@Table(name = "genero")
public class Genero implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero", nullable = false)
    private long idGenero;

    private String nombre;

    private String imagen;

    @Transient
    private MultipartFile imgTemp;

    public Genero(long idGenero) {
        this.idGenero = idGenero;
    }

    public Genero() {
    }
    
    
    
}
