package com.alkemy.disney.services;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.RespuestaPaginationDTO;

public interface PeliculaService {

    public RespuestaPaginationDTO getPeliculas(
            int page, int size, String sortBy, String sortDir,
            String nombre, Long idGenero
    );

    public PeliculaDTO getPelicula(Long id);

    public PeliculaDTO createPelicula(PeliculaDTO peliculaDTO);

    public PeliculaDTO updatePelicula(PeliculaDTO peliculaDTO, Long id);

    public void deletePelicula(Long id);



}
