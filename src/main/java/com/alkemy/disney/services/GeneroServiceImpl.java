package com.alkemy.disney.services;

import com.alkemy.disney.dto.GeneroDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class GeneroServiceImpl implements GeneroService{

    @Autowired private ModelMapper modelMapper;

    @Override
    public List<GeneroDTO> getGeneros() {
        return null;
    }

    @Override
    public Optional<GeneroDTO> getGenero(Long id) {
        return Optional.empty();
    }

    @Override
    public GeneroDTO createGenero(GeneroDTO generoDTO) {
        return null;
    }

    @Override
    public GeneroDTO updateGenero(GeneroDTO generoDTO, Long id) {
        return null;
    }

    @Override
    public void deleteGenero(Long id) {

    }
}
