package com.alkemy.disney.exceptions;

import com.alkemy.disney.dto.ErrorDetalleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetalleDTO> HandlerNotFound(
        ResourceNotFoundException resourceNotFoundException,
        WebRequest webRequest
    ){
        return new ResponseEntity<>(new ErrorDetalleDTO(
                new Date(),
                resourceNotFoundException.getMessage(),
                webRequest.getDescription(false)
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ErrorDetalleDTO> HandlerUserExist(
            UserExistException userExistException,
            WebRequest webRequest
    ){
        return new ResponseEntity<>(new ErrorDetalleDTO(
                new Date(),
                userExistException.getMessage(),
                webRequest.getDescription(false)
        ), HttpStatus.BAD_REQUEST);
    }





}
