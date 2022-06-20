package com.alkemy.disney.services;

import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.dto.RespuestaPaginationDTO;
import com.alkemy.disney.entities.Personaje;
import com.alkemy.disney.repositories.PersonajeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonajeServiceImpl implements PersonajeService{

    @Autowired private ModelMapper modelMapper;
    @Autowired private PersonajeRepository personajeRepository;


    @Override
    public RespuestaPaginationDTO getPersonajes(
            int page, int size, String sortBy, String sortDir
    ) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Personaje> personajes = personajeRepository.findAll(pageable);
        List<Personaje> listaPersonajes = personajes.getContent();

        List<PersonajeDTO> personajeDTOS = listaPersonajes.stream()
                .map(personaje -> personajeAPersonajeDTO(personaje))
                .collect(Collectors.toList());

        return new RespuestaPaginationDTO(
                personajeDTOS,
                personajes.getNumber(),
                personajes.getSize(),
                personajes.getTotalElements(),
                personajes.getTotalPages(),
                personajes.isLast()
        );
    }

    @Override
    public Optional<PersonajeDTO> getPersonaje(Long id) {
        return Optional.empty();
    }

    @Override
    public PersonajeDTO createPersonaje(PersonajeDTO personajeDTO) {
        return null;
    }

    @Override
    public PersonajeDTO updatePersonaje(PersonajeDTO personajeDTO, Long id) {
        return null;
    }

    @Override
    public void deletePersonaje(Long id) {

    }

    private PersonajeDTO personajeAPersonajeDTO(Personaje personaje){
        return modelMapper.map(personaje, PersonajeDTO.class);
    }

    private Personaje personajeDTOAPersonaje(PersonajeDTO personajeDTO){
        return modelMapper.map(personajeDTO, Personaje.class);
    }

}
