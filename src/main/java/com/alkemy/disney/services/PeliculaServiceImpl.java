package com.alkemy.disney.services;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.RespuestaPaginationDTO;
import com.alkemy.disney.entities.Genero;
import com.alkemy.disney.entities.Pelicula;
import com.alkemy.disney.entities.Personaje;
import com.alkemy.disney.exceptions.ResourceNotFoundException;
import com.alkemy.disney.repositories.GeneroRepository;
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
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired private PeliculaRepository peliculaRepository;


    @Autowired private PersonajeRepository personajeRepository;

    @Autowired private GeneroRepository generoRepository;

    @Autowired private ModelMapper modelMapper;


    @Override
    public RespuestaPaginationDTO getPeliculas(
            int page, int size, String sortBy, String sortDir,
            String titulo, Long idGenero
    ) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Pelicula> peliculaSeries = filterPeliculaSerie(titulo, idGenero, pageable);

        List<Pelicula> listaPelicula = peliculaSeries.getContent();

        List<PeliculaDTO> peliculaDTOS = listaPelicula.stream()
                .map(peliculaSerie -> peliculaAPeliculaDTO(peliculaSerie))
                .collect(Collectors.toList());

        return new RespuestaPaginationDTO(
                peliculaDTOS,
                peliculaSeries.getNumber(),
                peliculaSeries.getSize(),
                peliculaSeries.getTotalElements(),
                peliculaSeries.getTotalPages(),
                peliculaSeries.isLast()
        );
    }



    @Override
    public PeliculaDTO getPelicula(Long id) {
        Optional<Pelicula> peliculaSerie = peliculaRepository.findById(id);
        peliculaSerie.orElseThrow(() -> new ResourceNotFoundException("PeliculaSerie","id",id));
        return peliculaAPeliculaDTO(peliculaSerie.get());
    }

    @Override
    public PeliculaDTO createPelicula(PeliculaDTO peliculaDTO) {
        return peliculaAPeliculaDTO(
                peliculaRepository.save(peliculaDTOAPelicula(peliculaDTO))
        );
    }

    @Override
    public PeliculaDTO updatePelicula(PeliculaDTO peliculaDTO, Long id) {
        Optional<Pelicula> peliculaSerie = peliculaRepository.findById(id);
        peliculaSerie.orElseThrow(() -> new ResourceNotFoundException("PeliculaSerie","id",id));

        peliculaSerie.get().setImagen(peliculaDTO.getImagen());
        peliculaSerie.get().setTitulo(peliculaDTO.getTitulo());
        peliculaSerie.get().setCalificacion(peliculaDTO.getCalificacion());
        return peliculaAPeliculaDTO(peliculaRepository.save(peliculaSerie.get()));
    }

    @Override
    public void deletePelicula(Long id) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PeliculaSerie","id",id));
        peliculaRepository.delete(pelicula);

    }


    private Page<Pelicula> filterPeliculaSerie(String titulo, Long idGenero, Pageable pageable) {
        Page<Pelicula> peliculaSeries = null;
        Optional<Genero> genero = generoRepository.findById(idGenero);

        if(titulo == null && idGenero == 0){

            peliculaSeries = peliculaRepository.findAll(pageable);
        }else if(titulo != null && idGenero > 0){
            peliculaSeries = peliculaRepository.findPeliculaByTituloAndGenero(
                    titulo, genero.get(), pageable
            );
        } else if (titulo != null) {
            peliculaSeries = peliculaRepository.findPeliculaSeriesByTitulo(titulo, pageable);
        }else if (idGenero > 0){
            peliculaSeries = peliculaRepository.findPeliculaByGenero(
                    genero.get(), pageable
            );
        }

        return peliculaSeries;
    }


    @Override
    public PeliculaDTO agregarPersonaje(Long idPelicula, Long idPersonaje) {
        Optional<Pelicula> pelicula = peliculaRepository.findById(idPelicula);
        pelicula.orElseThrow(() -> new ResourceNotFoundException("Pelicula","id",idPelicula));

        Optional<Personaje> personaje = personajeRepository.findById(idPersonaje);
        personaje.orElseThrow(() -> new ResourceNotFoundException("Personaje","id",idPersonaje));

        pelicula.get().getPersonajes().add(personaje.get());
        return peliculaAPeliculaDTO(peliculaRepository.save(pelicula.get()));
    }

    @Override
    public PeliculaDTO eliminarPersonaje(Long idPelicula, Long idPersonaje) {
        Optional<Pelicula> pelicula = peliculaRepository.findById(idPelicula);
        pelicula.orElseThrow(() -> new ResourceNotFoundException("Pelicula","id",idPelicula));

        Optional<Personaje> personaje = personajeRepository.findById(idPersonaje);
        personaje.orElseThrow(() -> new ResourceNotFoundException("Personaje","id",idPersonaje));

        pelicula.get().getPersonajes().remove(personaje.get());
        return peliculaAPeliculaDTO(peliculaRepository.save(pelicula.get()));
    }


    private Pelicula peliculaDTOAPelicula(PeliculaDTO peliculaDTO){
        return modelMapper.map(peliculaDTO, Pelicula.class);
    }

    private PeliculaDTO peliculaAPeliculaDTO(Pelicula pelicula){
        return modelMapper.map(pelicula, PeliculaDTO.class);
    }


}
