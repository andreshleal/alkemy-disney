package com.alkemy.disney.repositories;

import java.util.Optional;

import com.alkemy.disney.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository extends JpaRepository<Rol, Long> {

    public Optional<Rol> findByNombre(String nombre);

}
