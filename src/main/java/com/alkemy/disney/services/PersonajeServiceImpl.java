package com.alkemy.disney.services;

import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.dto.RespuestaPaginationDTO;
import com.alkemy.disney.entities.Pelicula;
import com.alkemy.disney.entities.Personaje;
import com.alkemy.disney.exceptions.ResourceNotFoundException;
import com.alkemy.disney.repositories.PeliculaRepository;
import com.alkemy.disney.repositories.PersonajeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonajeServiceImpl implements PersonajeService{

    @Autowired private ModelMapper modelMapper;
    @Autowired private PersonajeRepository personajeRepository;
    @Autowired private PeliculaRepository peliculaRepository;


    @Override
    public RespuestaPaginationDTO getPersonajes(
            int page, int size, String sortBy, String sortDir,
            String nombre, int edad, Long idMovie
    ) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Personaje> personajes = filterPersonaje(nombre, edad, idMovie, pageable);
        //Page<Personaje> personajes = personajeRepository.findAll(pageable);
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
    public PersonajeDTO getPersonaje(Long id) {
        Optional<Personaje> personaje = personajeRepository.findById(id);
        personaje.orElseThrow(() -> new ResourceNotFoundException("Personaje","id",id));
        return personajeAPersonajeDTO(personaje.get());
    }

    @Override
    public PersonajeDTO createPersonaje(PersonajeDTO personajeDTO) {
        return personajeAPersonajeDTO(personajeRepository.save(personajeDTOAPersonaje(personajeDTO)));
    }

    @Override
    public PersonajeDTO updatePersonaje(PersonajeDTO personajeDTO, Long id) {
        Optional<Personaje> personaje = personajeRepository.findById(id);
        personaje.orElseThrow(() -> new ResourceNotFoundException("Personaje","id",id));

        personaje.get().setImagen(personajeDTO.getImagen());
        personaje.get().setNombre(personajeDTO.getNombre());
        personaje.get().setEdad(personajeDTO.getEdad());
        personaje.get().setPeso(personajeDTO.getPeso());
        personaje.get().setHistoria(personajeDTO.getHistoria());
        return personajeAPersonajeDTO(personajeRepository.save(personaje.get()));
    }

    @Override
    public void deletePersonaje(Long id) {
        Optional<Personaje> personaje = personajeRepository.findById(id);
        personaje.orElseThrow(() -> new ResourceNotFoundException("Personaje","id",id));
        personajeRepository.delete(personaje.get());
    }



    private Page<Personaje> filterPersonaje(String nombre, int edad, Long idMovie, Pageable pageable) {
        Page<Personaje> personajes = null;
        Optional<Pelicula> peliculaSerie = peliculaRepository.findById(idMovie);

        if(nombre == null && edad == 0 && idMovie == 0){
            personajes = personajeRepository.findAll(pageable);
        } else if (nombre != null && edad != 0 && idMovie == 0) {
            personajes = personajeRepository.findPersonajeByNombreAndEdad(nombre, edad, pageable);
        } else if (nombre != null && edad == 0 && idMovie > 0) {
            personajes = personajeRepository
                    .findPersonajeByNombreAndPeliculas(nombre, peliculaSerie.get(), pageable);
        }else if(nombre == null && edad != 0 && idMovie > 0){
            personajes = personajeRepository
                    .findPersonajeByEdadAndPeliculas(edad, peliculaSerie.get(), pageable);
        }else if (nombre != null){
            personajes = personajeRepository.findPersonajeByNombre(nombre,pageable);
        } else if (edad != 0) {
            personajes = personajeRepository.findPersonajeByEdad(edad, pageable);
        } else if (idMovie > 0) {
            personajes = personajeRepository.findPersonajeByPeliculas(peliculaSerie.get(), pageable);
        }
        return personajes;
    }


    private PersonajeDTO personajeAPersonajeDTO(Personaje personaje){
        return modelMapper.map(personaje, PersonajeDTO.class);
    }

    private Personaje personajeDTOAPersonaje(PersonajeDTO personajeDTO){
        return modelMapper.map(personajeDTO, Personaje.class);
    }

}
