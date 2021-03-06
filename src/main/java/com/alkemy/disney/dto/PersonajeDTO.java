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
public class PersonajeDTO {

    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Integer peso;
    private String historia;

    @JsonIgnoreProperties({"personajes","genero"})
    private List<Pelicula> peliculas;

}
