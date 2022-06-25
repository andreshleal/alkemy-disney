package com.alkemy.disney.repositories;

import com.alkemy.disney.entities.Genero;
import com.alkemy.disney.entities.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    Page<Pelicula> findPeliculaSeriesByTitulo(String titulo, Pageable pageable);

    Page<Pelicula> findPeliculaByGenero(Genero genero, Pageable pageable);

    Page<Pelicula> findPeliculaByTituloAndGenero(
            String titulo, Genero genero, Pageable pageable
    );


}
