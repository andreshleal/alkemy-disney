package com.alkemy.disney.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Setter
@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserExistException extends RuntimeException {

    public UserExistException(String nombreDelCampo, String valorDelCampo) {
        super(String.format("el usuario con %s = '%s' ya existe", nombreDelCampo, valorDelCampo));
        this.nombreDelCampo = nombreDelCampo;
        this.valorDelCampo = valorDelCampo;
    }

    private String nombreDelCampo;
    private String valorDelCampo;
}
