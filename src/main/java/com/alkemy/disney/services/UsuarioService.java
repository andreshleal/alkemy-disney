package com.alkemy.disney.services;

import com.alkemy.disney.dto.JWTAuthResponseDTO;
import com.alkemy.disney.dto.LoginDTO;
import com.alkemy.disney.dto.RegistroDTO;

import java.io.IOException;

public interface UsuarioService {

    public String registrarUsuario(RegistroDTO registroDTO) throws IOException;

    public JWTAuthResponseDTO autenticarUsuario(LoginDTO loginDTO);
}
