package com.example.curso.controller;

import com.example.curso.entity.Tutorial;
import com.example.curso.repository.TutorialRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para la gestión de tutoriales.
 * @see Tutorial
 * @Name TutorialController
 * @author Juan Manuel Losada
 */
@RestController
@RequestMapping("/api/v1/tutoriales")
public class TutorialController {

    /**
     * Repositorio para la gestión de tutoriales.
     */
    @Autowired
    public TutorialRepository tutorialRepository;

    /**
     * Obtiene todos los tutoriales o los busca por título.
     *
     * @param titulo título del tutorial a buscar (opcional)
     * @return lista de tutoriales encontrados
     */
    @Operation(summary = "Obtener todos los tutoriales o buscar por título")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutoriales encontrados",
                    content = @Content(schema = @Schema(implementation = Tutorial.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
    @GetMapping
    public ResponseEntity<List<Tutorial>> findAll(@Parameter(description = "Título del tutorial a buscar (opcional)") @RequestParam(required = false) String titulo) {
        try {
            List<Tutorial> tutoriales = titulo == null
                    ? tutorialRepository.findAll()
                    : tutorialRepository.findByTitulo(titulo);
            return new ResponseEntity<>(tutoriales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene un tutorial por id.
     *
     * @param id id del tutorial
     * @return tutorial encontrado
     */
    @Operation(summary = "Obtener un tutorial por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutorial encontrado",
                    content = @Content(schema = @Schema(implementation = Tutorial.class))),
            @ApiResponse(responseCode = "404", description = "Tutorial no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> findById(@Parameter(description = "Id del tutorial") @PathVariable Optional<Long> id) {
        return id.map(i -> tutorialRepository.findById(i)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("No existe el tutorial con id: " + i)))
                .orElseThrow(() -> new RuntimeException("No se especifica  el id del tutorial"));
    }

    /**
     * Crea un nuevo tutorial.
     *
     * @param tutorial tutorial a crear
     * @return tutorial creado
     */
    @Operation(summary = "Crear un nuevo tutorial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tutorial creado",
                    content = @Content(schema = @Schema(implementation = Tutorial.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
    @PostMapping
    public ResponseEntity<Tutorial> create(@Parameter(description = "Tutorial a crear") @RequestBody Tutorial tutorial) {
        try {
            return new ResponseEntity<>(tutorialRepository.save(tutorial), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualiza un tutorial.
     *
     * @param id      id del tutorial a actualizar
     * @param tutorial datos del tutorial a actualizar
     * @return tutorial actualizado
     */
    @Operation(summary = "Actualizar un tutorial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutorial actualizado",
                    content = @Content(schema = @Schema(implementation = Tutorial.class))),
            @ApiResponse(responseCode = "404", description = "Tutorial no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<Tutorial> update(@Parameter(description = "Id del tutorial a actualizar") @PathVariable Long id, @Parameter(description = "Tutorial a actualizar") @RequestBody Tutorial tutorial) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setPublicado(tutorial.getPublicado());
            _tutorial.setDescripcion(tutorial.getDescripcion());
            _tutorial.setTitulo(tutorial.getTitulo());
            return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un tutorial.
     *
     * @param id id del tutorial a eliminar
     */
    @Operation(summary = "Eliminar un tutorial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tutorial eliminado"),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@Parameter(description = "Id del tutorial a eliminar") @PathVariable Long id) {
        try {
            tutorialRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina todos los tutoriales.
     */
    @Operation(summary = "Eliminar todos los tutoriales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tutoriales eliminados"),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            tutorialRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Crea un nuevo tutorial.
     *
     * @param tutorial tutorial a crear
     * @return tutorial creado
     */
    @Operation(summary = "Crear un nuevo tutorial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tutorial creado",
                    content = @Content(schema = @Schema(implementation = Tutorial.class))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
    @PostMapping("/publicado")
    public ResponseEntity<Tutorial> createPublicado(@Parameter(description = "Tutorial a crear") @RequestBody Tutorial tutorial) {
        try {
            return new ResponseEntity<>(tutorialRepository.save(tutorial), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Encuentra todos los tutoriales publicados.
     *
     * @return lista de tutoriales publicados
     */
    @Operation(summary = "Encuentra todos los tutoriales publicados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tutoriales publicados",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Tutorial.class)))),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content) })
    @GetMapping("/publicado")
    public ResponseEntity<List<Tutorial>> findByPublicadoTrue() {
        try {
            List<Tutorial> tutorials = tutorialRepository.findByPublicado(true);
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}