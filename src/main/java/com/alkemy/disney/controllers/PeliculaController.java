package com.alkemy.disney.controllers;

import com.alkemy.disney.dto.PeliculaDTO;
import com.alkemy.disney.dto.RespuestaPaginationDTO;
import com.alkemy.disney.services.PeliculaService;
import com.alkemy.disney.utilities.PaginationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class PeliculaController {

    @Autowired private PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<RespuestaPaginationDTO> getPeliculasSeries(
            @RequestParam(defaultValue = PaginationConstant.NUMERO_PAGINA, required = false) int page,
            @RequestParam(defaultValue = PaginationConstant.TAMANO_PAGINA, required = false) int size,
            @RequestParam(defaultValue = PaginationConstant.ORDENAR_POR_CAMPO, required = false) String sortBy,
            @RequestParam(defaultValue = PaginationConstant.ORDENAR_DIRECCION, required = false) String sortDir,
            @RequestParam(name = "name", required = false) String titulo,
            @RequestParam(defaultValue = PaginationConstant.ID_GENERO, name = "genre", required = false ) Long id
    ){
        return new ResponseEntity<>(peliculaService
                .getPeliculas(page, size, sortBy, sortDir, titulo, id), HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> getPeliculaSerie(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(peliculaService.getPelicula(id));
    }

    @PostMapping
    public ResponseEntity<PeliculaDTO> savePeliculaSerie(@RequestBody PeliculaDTO peliculaDTO){
        return new ResponseEntity<>(peliculaService
                .createPelicula(peliculaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> updatePelicuaSerie(
            @PathVariable Long id,
            @RequestBody PeliculaDTO peliculaDTO
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                peliculaService.updatePelicula(peliculaDTO, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePeliculaSerie(@PathVariable(name = "id") Long id){
        peliculaService.deletePelicula(id);
    }

}
