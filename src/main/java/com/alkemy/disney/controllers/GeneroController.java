package com.alkemy.disney.controllers;

import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.services.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    //    /v3/api-docs
    // swagger-ui/index.html

    @Autowired private GeneroService generoService;

    @Operation(summary = "Obtiene una lista con todos los generos de pelicula")
    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getGeneros(){
        return new ResponseEntity<>(generoService.getGeneros(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un genero especifico pasando su id")
    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> getGenero(@PathVariable("id") Long id){
        return new ResponseEntity<>(generoService.getGenero(id), HttpStatus.OK);
    }

    @Operation(summary = "almacena un genero nuevo en la base de datos")
    @PostMapping
    public ResponseEntity<GeneroDTO> createGenero(@RequestBody GeneroDTO generoDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(generoService.createGenero(generoDTO));
    }

    @Operation(summary = "actualiza un genero de acuerdo a su id")
    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO> updateGenero(
            @PathVariable Long id, @RequestBody GeneroDTO generoDTO
    ){
        return ResponseEntity.status(201).body(generoService.updateGenero(generoDTO, id));
    }

    @Operation(summary = "elimina un genero de acuerdo a su id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenero(@PathVariable("id") Long id){
        generoService.deleteGenero(id);
    }


}
