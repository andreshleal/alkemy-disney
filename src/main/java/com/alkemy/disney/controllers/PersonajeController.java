package com.alkemy.disney.controllers;

import com.alkemy.disney.dto.PersonajeDTO;
import com.alkemy.disney.dto.RespuestaPaginationDTO;
import com.alkemy.disney.services.PersonajeService;
import com.alkemy.disney.utilities.PaginationConstant;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired private PersonajeService personajeService;


    @Operation(summary = "Obtiene una lista de personajes, con paginación",
            description = "pasando parametros en los headers, se obtiene una lista filtrada y paginada"
    )
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


    @Operation(summary = "almacena un Personaje en la base de datos")
    @PostMapping
    public ResponseEntity<PersonajeDTO> savePersonaje(@RequestBody PersonajeDTO personajeDTO){
        return new ResponseEntity<>(personajeService.createPersonaje(personajeDTO),HttpStatus.CREATED);
    }

    @Operation(summary = "obtiene unpersonaje de la bse de datos de acuerdo a su id")
    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getPersonaje(@PathVariable("id") Long id){
        return new ResponseEntity<>(personajeService.getPersonaje(id), HttpStatus.OK);
    }

    @Operation(summary = "actualiza un personaje, enviando en el cuerpo los nuevos datos",
            description = "se debe pasar el id del personaje como variable en la url"
    )
    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> updatePersonaje(
            @PathVariable Long id, @RequestBody PersonajeDTO personajeDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personajeService.updatePersonaje(personajeDTO, id));
    }

    @Operation(summary = "elimina un personaje de acuerdo a su id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonaje(@PathVariable("id") Long id){
        personajeService.deletePersonaje(id);
    }


}
