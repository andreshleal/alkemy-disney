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
import com.alkemy.disney.services.UsuarioService;
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

    @Autowired private UsuarioService usuarioService;

    @Operation(summary = "registra usuarios nuevos en el sistema")
    @PostMapping("/register")
    public ResponseEntity<String> registrarUsuario(@RequestBody RegistroDTO registroDTO) throws IOException {
        return new ResponseEntity<>(usuarioService.registrarUsuario(registroDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "autentica usuarios, si es valido devuelve un token para poder usar la API")
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
        return new ResponseEntity<>(usuarioService.autenticarUsuario(loginDTO), HttpStatus.OK);
    }




}
