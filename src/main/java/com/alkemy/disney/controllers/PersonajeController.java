package com.alkemy.disney.controllers;

import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.dto.RespuestaPaginationDTO;
import com.alkemy.disney.services.PersonajeService;
import com.alkemy.disney.utilities.PaginationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired private PersonajeService personajeService;


    @GetMapping
    public ResponseEntity<RespuestaPaginationDTO> getPersonajes(
            @RequestParam(defaultValue = PaginationConstant.NUMERO_PAGINA, required = false) int page,
            @RequestParam(defaultValue = PaginationConstant.TAMANO_PAGINA, required = false) int size,
            @RequestParam(defaultValue = PaginationConstant.ORDENAR_POR_CAMPO,required = false) String sortBy,
            @RequestParam(defaultValue = PaginationConstant.ORDENAR_DIRECCION, required = false) String sortDir,
            @RequestParam(name = "name", required = false) String nombre,
            @RequestParam(name = "age", defaultValue = PaginationConstant.EDAD, required = false) int edad,
            @RequestParam(name = "movies", defaultValue = PaginationConstant.ID_MOVIE, required = false) Long idMovie
    ){
        return ResponseEntity.ok()
                .body(personajeService
                        .getPersonajes(page, size, sortBy, sortDir, nombre, edad, idMovie));
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO> savePersonaje(@RequestBody PersonajeDTO personajeDTO){
        return new ResponseEntity<>(personajeService.createPersonaje(personajeDTO),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getPersonaje(@PathVariable("id") Long id){
        return new ResponseEntity<>(personajeService.getPersonaje(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> updatePersonaje(
            @PathVariable Long id, @RequestBody PersonajeDTO personajeDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personajeService.updatePersonaje(personajeDTO, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonaje(@PathVariable("id") Long id){
        personajeService.deletePersonaje(id);
    }


}
