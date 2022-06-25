package com.alkemy.disney.dto;

import com.alkemy.disney.entities.Genero;
import com.alkemy.disney.entities.Personaje;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaDTO {

    private Long id;
    private String imagen;
    private String titulo;
    private Date fechaCreacion;
    private Integer calificacion;

    @JsonIgnoreProperties({"peliculas"})
    private List<Personaje> personajes;

    @JsonIgnoreProperties({"peliculas"})
    private Genero genero;

}
