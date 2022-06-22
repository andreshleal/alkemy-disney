package com.alkemy.disney.repositories;

import com.alkemy.disney.entities.Personaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {


    Page<Personaje> findPersonajeByNombreAndEdad(String nombre, int edad, Pageable pageable);

}
