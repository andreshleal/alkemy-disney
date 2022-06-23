package com.alkemy.disney.services;

import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.dto.RespuestaPaginationDTO;
import java.util.Optional;

public interface PersonajeService {

    public RespuestaPaginationDTO getPersonajes(
            int page, int size, String sortBy, String sortDir,
            String nombre, int edad, Long idMovie
    );

    public PersonajeDTO getPersonaje(Long id);

    public PersonajeDTO createPersonaje(PersonajeDTO personajeDTO);

    public PersonajeDTO updatePersonaje(PersonajeDTO personajeDTO, Long id);

    public void deletePersonaje(Long id);

}
