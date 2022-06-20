package com.alkemy.disney.services;

import com.alkemy.disney.dto.PeliculaSerieDTO;

import java.util.List;
import java.util.Optional;

public interface PeliculaSerieService {

    public List<PeliculaSerieDTO> getPeliculasSeries();

    public Optional<PeliculaSerieDTO> getPeliculaSerie(Long id);

    public PeliculaSerieDTO createPeliculaSerie(PeliculaSerieDTO peliculaSerieDTO);

    public PeliculaSerieDTO updatePeliculaSerie(PeliculaSerieDTO peliculaSerieDTO, Long id);

    public void deletePeliculaSerie(Long id);



}
