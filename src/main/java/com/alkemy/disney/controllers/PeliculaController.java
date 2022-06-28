package com.alkemy.disney.controllers;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.RespuestaPaginationDTO;
import com.alkemy.disney.services.PeliculaService;
import com.alkemy.disney.utilities.PaginationConstant;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class PeliculaController {

    @Autowired private PeliculaService peliculaService;

    @Operation(summary = "Obtiene una lista de peliculas o series, con paginación",
            description = "pasando parametros en los headers, se obtiene una lista filtrada y paginada"
    )
    @GetMapping
    public ResponseEntity<RespuestaPaginationDTO> getPeliculasSeries(
            @RequestParam(defaultValue = PaginationConstant.NUMERO_PAGINA, required = false) int page,
            @RequestParam(defaultValue = PaginationConstant.TAMANO_PAGINA, required = false) int size,
            @RequestParam(defaultValue = PaginationConstant.ORDENAR_POR_CAMPO, required = false) String sortBy,
            @RequestParam(name = "order", defaultValue = PaginationConstant.ORDENAR_DIRECCION, required = false) String sortDir,
            @RequestParam(name = "name", required = false) String titulo,
            @RequestParam(defaultValue = PaginationConstant.ID_GENERO, name = "genre", required = false ) Long id
    ){
        return new ResponseEntity<>(peliculaService
                .getPeliculas(page, size, sortBy, sortDir, titulo, id), HttpStatus.OK);
    }


    @Operation(summary = "Obtiene una pelicula de acuerdo a su id")
    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> getPeliculaSerie(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(peliculaService.getPelicula(id));
    }

    @Operation(summary = "almacena una pelicula o serie en la base de datos",
            description = "si se le envian personajes, los vincula a la pelicula, se debe enviar el genero de la pelicula"
    )
    @PostMapping
    public ResponseEntity<PeliculaDTO> savePeliculaSerie(@RequestBody PeliculaDTO peliculaDTO){
        return new ResponseEntity<>(peliculaService
                .createPelicula(peliculaDTO), HttpStatus.CREATED);
    }


    @Operation(summary = "actualiza una pelicula o serie, enviando en el cuerpo los nuevos datos",
        description = "se debe pasar el id de la pelicula como variable en la url"
    )
    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> updatePelicuaSerie(
            @PathVariable Long id,
            @RequestBody PeliculaDTO peliculaDTO
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                peliculaService.updatePelicula(peliculaDTO, id));
    }

    @Operation(summary = "elimina una pelicula o serie de acuerdo a su id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePeliculaSerie(@PathVariable(name = "id") Long id){
        peliculaService.deletePelicula(id);
    }


    @Operation(summary = "agrega un personaje a una pelicula o serie pasando su id")
    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<PeliculaDTO> agregarPersonaje(
            @PathVariable("idMovie") Long idMovie,
            @PathVariable("idCharacter") Long idCharacter
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(peliculaService.agregarPersonaje(idMovie, idCharacter));
    }


    @Operation(summary = "elimina un personaje de una pelicula o serie pasando su id")
    @DeleteMapping ("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<PeliculaDTO> eliminarPersonaje(
            @PathVariable("idMovie") Long idMovie,
            @PathVariable("idCharacter") Long idCharacter
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(peliculaService.eliminarPersonaje(idMovie, idCharacter));
    }


}












