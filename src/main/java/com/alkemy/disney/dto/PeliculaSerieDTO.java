package com.alkemy.disney.dto;

import com.alkemy.disney.entities.Genero;
import com.alkemy.disney.entities.Personaje;
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
public class PeliculaSerieDTO {

    private Long id;
    private String imagen;
    private String titulo;
    private Date fechaCreacion;
    private Integer calificacion;
    private List<Personaje> personajes;
    private Genero genero;

}
