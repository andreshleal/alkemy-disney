package com.alkemy.disney.services;

import com.alkemy.disney.dto.JWTAuthResponseDTO;
import com.alkemy.disney.dto.LoginDTO;
import com.alkemy.disney.dto.RegistroDTO;
import com.alkemy.disney.entities.Rol;
import com.alkemy.disney.entities.Usuario;
import com.alkemy.disney.exceptions.ResourceNotFoundException;
import com.alkemy.disney.exceptions.UserExistException;
import com.alkemy.disney.repositories.RolRepository;
import com.alkemy.disney.repositories.UsuarioRepository;
import com.alkemy.disney.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private MailService mailService;

    @Autowired private AuthenticationManager authenticationManager;

    @Autowired private UsuarioRepository usuarioRepositorio;

    @Autowired private RolRepository rolRepositorio;

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private JwtTokenProvider jwtTokenProvider;


    @Override
    public String registrarUsuario(RegistroDTO registroDTO) throws IOException {

        if(usuarioRepositorio.existsByUsername(registroDTO.getUsername()).get()) {
            throw new UserExistException("Username",registroDTO.getUsername());
        }

        if(usuarioRepositorio.existsByEmail(registroDTO.getEmail()).get()) {
            throw new UserExistException("Email",registroDTO.getEmail());
        }


        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setUsername(registroDTO.getUsername());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        Rol roles = rolRepositorio.findByNombre("ROLE_USER").get();
        usuario.setRoles(Collections.singleton(roles));

        mailService.sendEmail(usuario.getEmail());
        usuarioRepositorio.save(usuario);
        return "Usuario registrado exitosamente";
    }

    @Override
    public JWTAuthResponseDTO autenticarUsuario(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO
                        .getUsernameOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // obtener el token del jwtprovider
        String token = jwtTokenProvider.generarToken(authentication);

        return new JWTAuthResponseDTO(token);
    }
}
