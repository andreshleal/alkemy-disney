package com.alkemy.disney.services;

import com.alkemy.disney.dto.PeliculaSerieDTO;
import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.dto.RespuestaPaginationDTO;
import com.alkemy.disney.entities.Personaje;

import java.util.List;
import java.util.Optional;

public interface PersonajeService {

    public RespuestaPaginationDTO getPersonajes(int page, int size, String sortBy, String sortDir);

    public Optional<PersonajeDTO> getPersonaje(Long id);

    public PersonajeDTO createPersonaje(PersonajeDTO personajeDTO);

    public PersonajeDTO updatePersonaje(PersonajeDTO personajeDTO, Long id);

    public void deletePersonaje(Long id);
}
