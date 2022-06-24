package com.alkemy.disney.repositories;

import com.alkemy.disney.entities.Genero;
import com.alkemy.disney.entities.PeliculaSerie;
import com.alkemy.disney.entities.Personaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Long> {

    Page<PeliculaSerie> findPeliculaSeriesByTitulo(String titulo, Pageable pageable);

    Page<PeliculaSerie> findPeliculaSerieByGenero(Genero genero, Pageable pageable);

    Page<PeliculaSerie> findPeliculaSeriesByTituloAndGenero(
            String titulo, Genero genero, Pageable pageable
    );


}
