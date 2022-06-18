package com.alkemy.disney.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personajes")
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagen;
    private String nombre;
    private Integer edad;
    private Integer peso;
    private String historia;

    @JsonBackReference
    @JoinTable(
            name = "personaje_pelicula_serie",
            joinColumns = @JoinColumn(name = "personaje_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_serie_id", referencedColumnName = "id")
    )
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PeliculaSerie> peliculaSeries;
}
