package com.alkemy.disney.controllers;

import com.alkemy.disney.dto.PeliculaSerieDTO;
import com.alkemy.disney.dto.RespuestaPaginationDTO;
import com.alkemy.disney.services.PeliculaSerieService;
import com.alkemy.disney.utilities.PaginationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class PeliculaSerieController {

    @Autowired private PeliculaSerieService peliculaSerieService;

    @GetMapping
    public ResponseEntity<RespuestaPaginationDTO> getPeliculasSeries(
            @RequestParam(defaultValue = PaginationConstant.NUMERO_PAGINA, required = false) int page,
            @RequestParam(defaultValue = PaginationConstant.TAMANO_PAGINA, required = false) int size,
            @RequestParam(defaultValue = PaginationConstant.ORDENAR_POR_CAMPO, required = false) String sortBy,
            @RequestParam(defaultValue = PaginationConstant.ORDENAR_DIRECCION, required = false) String sortDir,
            @RequestParam(name = "name", required = false) String titulo,
            @RequestParam(defaultValue = PaginationConstant.ID_GENERO, name = "genre", required = false ) Long id
    ){
        return new ResponseEntity<>(peliculaSerieService
                .getPeliculasSeries(page, size, sortBy, sortDir, titulo, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaSerieDTO> getPeliculaSerie(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(peliculaSerieService.getPeliculaSerie(id));
    }

    @PostMapping
    public ResponseEntity<PeliculaSerieDTO> savePeliculaSerie(@RequestBody PeliculaSerieDTO peliculaSerieDTO){
        return new ResponseEntity<>(peliculaSerieService
                .createPeliculaSerie(peliculaSerieDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaSerieDTO> updatePelicuaSerie(
            @PathVariable Long id,
            @RequestBody PeliculaSerieDTO peliculaSerieDTO
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                peliculaSerieService.updatePeliculaSerie(peliculaSerieDTO, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePeliculaSerie(@PathVariable(name = "id") Long id){
        peliculaSerieService.deletePeliculaSerie(id);
    }

}
