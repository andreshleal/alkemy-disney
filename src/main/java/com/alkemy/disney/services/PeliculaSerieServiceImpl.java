package com.alkemy.disney.services;

import com.alkemy.disney.dto.PeliculaSerieDTO;
import com.alkemy.disney.dto.RespuestaPaginationDTO;
import com.alkemy.disney.entities.PeliculaSerie;
import com.alkemy.disney.entities.Personaje;
import com.alkemy.disney.exceptions.ResourceNotFoundException;
import com.alkemy.disney.repositories.PeliculaSerieRepository;
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
public class PeliculaSerieServiceImpl implements PeliculaSerieService{

    @Autowired private PeliculaSerieRepository peliculaSerieRepository;


    @Autowired private PersonajeRepository personajeRepository;

    @Autowired private ModelMapper modelMapper;


    @Override
    public RespuestaPaginationDTO getPeliculasSeries(
            int page, int size, String sortBy, String sortDir,
            String titulo, Long idPersonaje
    ) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<PeliculaSerie> peliculaSeries = filterPeliculaSerie(titulo, idPersonaje, pageable);

        List<PeliculaSerie> listaPeliculaSerie = peliculaSeries.getContent();

        List<PeliculaSerieDTO> peliculaSerieDTOS = listaPeliculaSerie.stream()
                .map(peliculaSerie -> peliculaSerieAPeliculaSerieDTO(peliculaSerie))
                .collect(Collectors.toList());

        return new RespuestaPaginationDTO(
                peliculaSerieDTOS,
                peliculaSeries.getNumber(),
                peliculaSeries.getSize(),
                peliculaSeries.getTotalElements(),
                peliculaSeries.getTotalPages(),
                peliculaSeries.isLast()
        );
    }



    @Override
    public PeliculaSerieDTO getPeliculaSerie(Long id) {
        Optional<PeliculaSerie> peliculaSerie = peliculaSerieRepository.findById(id);
        peliculaSerie.orElseThrow(() -> new ResourceNotFoundException("PeliculaSerie","id",id));
        return peliculaSerieAPeliculaSerieDTO(peliculaSerie.get());
    }

    @Override
    public PeliculaSerieDTO createPeliculaSerie(PeliculaSerieDTO peliculaSerieDTO) {
        return peliculaSerieAPeliculaSerieDTO(
                peliculaSerieRepository.save(peliculaSerieDTOAPeliculaSerie(peliculaSerieDTO))
        );
    }

    @Override
    public PeliculaSerieDTO updatePeliculaSerie(PeliculaSerieDTO peliculaSerieDTO, Long id) {
        Optional<PeliculaSerie> peliculaSerie = peliculaSerieRepository.findById(id);
        peliculaSerie.orElseThrow(() -> new ResourceNotFoundException("PeliculaSerie","id",id));

        peliculaSerie.get().setImagen(peliculaSerieDTO.getImagen());
        peliculaSerie.get().setTitulo(peliculaSerieDTO.getTitulo());
        peliculaSerie.get().setCalificacion(peliculaSerieDTO.getCalificacion());
        return peliculaSerieAPeliculaSerieDTO(peliculaSerieRepository.save(peliculaSerie.get()));
    }

    @Override
    public void deletePeliculaSerie(Long id) {
        PeliculaSerie peliculaSerie = peliculaSerieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PeliculaSerie","id",id));
        peliculaSerieRepository.delete(peliculaSerie);

    }



    private Page<PeliculaSerie> filterPeliculaSerie(String titulo, Long idPersonaje, Pageable pageable) {
        Page<PeliculaSerie> peliculaSeries = null;
        Optional<Personaje> personaje = personajeRepository.findById(idPersonaje);

        if(titulo == null && idPersonaje == 0){
            peliculaSeries = peliculaSerieRepository.findAll(pageable);
        }else if(titulo != null && idPersonaje > 0){
            peliculaSeries = peliculaSerieRepository.findPeliculaSeriesByTituloAndPersonajes(
                    titulo, personaje.get(), pageable
            );
        } else if (titulo != null) {
            peliculaSeries = peliculaSerieRepository.findPeliculaSeriesByTitulo(titulo, pageable);
        }else if (idPersonaje > 0){
            peliculaSeries = peliculaSerieRepository.findPeliculaSerieByPersonajes(
                    personaje.get(), pageable
            );
        }

        return peliculaSeries;
    }


    private PeliculaSerie peliculaSerieDTOAPeliculaSerie(PeliculaSerieDTO peliculaSerieDTO){
        return modelMapper.map(peliculaSerieDTO, PeliculaSerie.class);
    }

    private PeliculaSerieDTO peliculaSerieAPeliculaSerieDTO(PeliculaSerie peliculaSerie){
        return modelMapper.map(peliculaSerie, PeliculaSerieDTO.class);
    }


}
