package com.jg.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Data
@Table(name = "rodaje")
public class Rodaje{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rodaje", nullable = false)
    private long idRodaje;
    
    @NotBlank
    private String titulo;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private int calificacion;

    private String imagen;

    @Transient
    private MultipartFile imgTemp;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "rodaje_genero",
            joinColumns = @JoinColumn(name = "id_rodaje"),
            inverseJoinColumns = @JoinColumn(name = "id_genero")
    )
    private Set<Genero> generos = new HashSet<>();

}
