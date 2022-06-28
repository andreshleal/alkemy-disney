package com.alkemy.disney.controllers;



import java.io.IOException;
import java.util.Collections;

import com.alkemy.disney.dto.LoginDTO;
import com.alkemy.disney.dto.RegistroDTO;
import com.alkemy.disney.entities.Rol;
import com.alkemy.disney.entities.Usuario;
import com.alkemy.disney.repositories.RolRepository;
import com.alkemy.disney.repositories.UsuarioRepository;
import com.alkemy.disney.dto.JWTAuthResponseDTO;
import com.alkemy.disney.security.JwtTokenProvider;
import com.alkemy.disney.services.MailService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private MailService mailService;

    @Autowired private AuthenticationManager authenticationManager;

    @Autowired private UsuarioRepository usuarioRepositorio;

    @Autowired private RolRepository rolRepositorio;

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "autentica usuarios, si es valido devuelve un token para poder usar la API")
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO
                        .getUsernameOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // obtener el token del jwtprovider
        String token = jwtTokenProvider.generarToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }

    @Operation(summary = "registra usuarios nuevos en el sistema")
    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO) throws IOException {
//        return new ResponseEntity<>(registroDTO.getUsername()+registroDTO.getNombre()
//                +registroDTO.getEmail()+registroDTO.getPassword()+"",HttpStatus.OK);

        if(usuarioRepositorio.existsByUsername(registroDTO.getUsername())) {
            return new ResponseEntity<>("el nombre de usuario ya existe",HttpStatus.BAD_REQUEST);
        }

        if(usuarioRepositorio.existsByEmail(registroDTO.getEmail())) {
            return new ResponseEntity<>("el email ya existe", HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
    }


}
