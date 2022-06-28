package com.alkemy.disney.repositories;

import java.util.Optional;

import com.alkemy.disney.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

    public Optional<Usuario> findByUsername(String username);

    public Optional<Usuario> findByUsernameOrEmail(String username, String email);

    public Optional<Boolean> existsByUsername(String username);

    public Optional<Boolean> existsByEmail(String email);

}