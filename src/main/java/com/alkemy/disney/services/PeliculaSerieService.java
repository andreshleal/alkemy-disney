package com.alkemy.disney.services;

import com.alkemy.disney.dto.PeliculaSerieDTO;
import com.alkemy.disney.dto.RespuestaPaginationDTO;

import java.util.List;
import java.util.Optional;

public interface PeliculaSerieService {

    public RespuestaPaginationDTO getPeliculasSeries(
            int page, int size, String sortBy, String sortDir,
            String nombre, Long idGenero
    );

    public PeliculaSerieDTO getPeliculaSerie(Long id);

    public PeliculaSerieDTO createPeliculaSerie(PeliculaSerieDTO peliculaSerieDTO);

    public PeliculaSerieDTO updatePeliculaSerie(PeliculaSerieDTO peliculaSerieDTO, Long id);

    public void deletePeliculaSerie(Long id);



}
