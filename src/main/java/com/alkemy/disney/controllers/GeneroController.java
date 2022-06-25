package com.alkemy.disney.controllers;

import com.alkemy.disney.dto.GeneroDTO;
import com.alkemy.disney.services.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired private GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getGeneros(){
        return new ResponseEntity<>(generoService.getGeneros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> getGenero(@PathVariable("id") Long id){
        return new ResponseEntity<>(generoService.getGenero(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> createGenero(@RequestBody GeneroDTO generoDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(generoService.createGenero(generoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO> updateGenero(
            @PathVariable Long id, @RequestBody GeneroDTO generoDTO
    ){
        return ResponseEntity.status(201).body(generoService.updateGenero(generoDTO, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenero(@PathVariable("id") Long id){
        generoService.deleteGenero(id);
    }


}
