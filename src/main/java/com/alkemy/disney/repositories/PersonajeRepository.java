package com.alkemy.disney.repositories;

import com.alkemy.disney.entities.Pelicula;
import com.alkemy.disney.entities.Personaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {


    Page<Personaje> findPersonajeByNombre(String nombre, Pageable pageable);

    Page<Personaje> findPersonajeByEdad(int edad, Pageable pageable);

    Page<Personaje> findPersonajeByPeliculas(Pelicula pelicula, Pageable pageable);

    Page<Personaje> findPersonajeByNombreAndEdad(String nombre, int edad, Pageable pageable);

    Page<Personaje> findPersonajeByNombreAndPeliculas(
            String nombre, Pelicula pelicula, Pageable pageable
    );

    Page<Personaje> findPersonajeByEdadAndPeliculas(
            int edad, Pelicula pelicula, Pageable pageable
    );

    Page<Personaje> findPersonajeByNombreAndEdadAndPeliculas(
            String nombre, int edad, Pelicula pelicula,
            Pageable pageable
    );




}
