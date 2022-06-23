package com.alkemy.disney.repositories;

import com.alkemy.disney.entities.PeliculaSerie;
import com.alkemy.disney.entities.Personaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {


    Page<Personaje> findPersonajeByNombre(String nombre, Pageable pageable);

    Page<Personaje> findPersonajeByEdad(int edad, Pageable pageable);

    Page<Personaje> findPersonajeByPeliculaSeries(PeliculaSerie peliculaSerie, Pageable pageable);

    Page<Personaje> findPersonajeByNombreAndEdad(String nombre, int edad, Pageable pageable);

    Page<Personaje> findPersonajeByNombreAndPeliculaSeries(
            String nombre, PeliculaSerie peliculaSerie, Pageable pageable
    );

    Page<Personaje> findPersonajeByEdadAndPeliculaSeries(
            int edad, PeliculaSerie peliculaSerie, Pageable pageable
    );

    Page<Personaje> findPersonajeByNombreAndEdadAndPeliculaSeries(
            String nombre, int edad, PeliculaSerie peliculaSerie,
            Pageable pageable
    );




}
