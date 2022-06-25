package com.alkemy.disney.services;

import com.alkemy.disney.dto.GeneroDTO;
import java.util.List;
import java.util.Optional;

public interface GeneroService {

    public List<GeneroDTO> getGeneros();

    public GeneroDTO getGenero(Long id);

    public GeneroDTO createGenero(GeneroDTO generoDTO);

    public GeneroDTO updateGenero(GeneroDTO generoDTO, Long id);

    public void deleteGenero(Long id);

}
