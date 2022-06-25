package com.alkemy.disney.dto;

import com.alkemy.disney.entities.Pelicula;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneroDTO {

    private Long id;
    private String nombre;
    private String imagen;
    @JsonIgnoreProperties({"genero","personajes"})
    private List<Pelicula> peliculas;


}
