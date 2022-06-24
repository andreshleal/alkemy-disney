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
@Table(name = "generos")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String imagen;

    // hola homer from and
    @JsonBackReference
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "genero",orphanRemoval = true)
    private List<PeliculaSerie> peliculaSeries;


}
