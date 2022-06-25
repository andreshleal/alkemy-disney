package com.alkemy.disney.services;

import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.entities.Genero;
import com.alkemy.disney.exceptions.ResourceNotFoundException;
import com.alkemy.disney.repositories.GeneroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeneroServiceImpl implements GeneroService{

    @Autowired private GeneroRepository generoRepository;

    @Autowired private ModelMapper modelMapper;

    @Override
    public List<GeneroDTO> getGeneros() {
        List<Genero> generos = generoRepository.findAll();
        List<GeneroDTO> generoDTOS = generos.stream().map((genero) -> generoAGeneroDTO(genero))
                .collect(Collectors.toList());
        return generoDTOS;
    }

    @Override
    public GeneroDTO getGenero(Long id) {
        Optional<Genero> genero = generoRepository.findById(id);
        genero.orElseThrow(() -> new ResourceNotFoundException("Genero","id",id));
        return generoAGeneroDTO(genero.get());
    }

    @Override
    public GeneroDTO createGenero(GeneroDTO generoDTO) {
        return generoAGeneroDTO(generoRepository.save(generoDTOAGenero(generoDTO)));
    }

    @Override
    public GeneroDTO updateGenero(GeneroDTO generoDTO, Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genero","id",id));
        genero.setNombre(generoDTO.getNombre());
        genero.setImagen(generoDTO.getImagen());

        return generoAGeneroDTO(generoRepository.save(genero));
    }

    @Override
    public void deleteGenero(Long id) {
        Optional<Genero> genero = generoRepository.findById(id);
        genero.orElseThrow(() -> new ResourceNotFoundException("Genero","id",id));
        generoRepository.delete(genero.get());
    }



    private Genero generoDTOAGenero(GeneroDTO generoDTO){
        return modelMapper.map(generoDTO, Genero.class);
    }

    private GeneroDTO generoAGeneroDTO(Genero genero){
        return modelMapper.map(genero, GeneroDTO.class);
    }

}
