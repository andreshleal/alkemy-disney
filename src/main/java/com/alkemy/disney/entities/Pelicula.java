package com.alkemy.disney.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagen;
    private String titulo;
    private Date fechaCreacion;
    private Integer calificacion;


    @JoinTable(
            name = "personaje_pelicula",
            joinColumns = @JoinColumn(name = "pelicula_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="personaje_id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Personaje> personajes;


    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;


}
