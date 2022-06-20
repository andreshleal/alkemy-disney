package com.alkemy.disney.services;

import com.alkemy.disney.dto.PeliculaSerieDTO;

import java.util.List;
import java.util.Optional;

public class PeliculaSerieServiceImpl implements PeliculaSerieService{
    @Override
    public List<PeliculaSerieDTO> getPeliculasSeries() {
        return null;
    }

    @Override
    public Optional<PeliculaSerieDTO> getPeliculaSerie(Long id) {
        return Optional.empty();
    }

    @Override
    public PeliculaSerieDTO createPeliculaSerie(PeliculaSerieDTO peliculaSerieDTO) {
        return null;
    }

    @Override
    public PeliculaSerieDTO updatePeliculaSerie(PeliculaSerieDTO peliculaSerieDTO, Long id) {
        return null;
    }

    @Override
    public void deletePeliculaSerie(Long id) {

    }
}
