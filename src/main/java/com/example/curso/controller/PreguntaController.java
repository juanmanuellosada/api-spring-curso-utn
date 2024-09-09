package com.example.curso.controller;

import com.example.curso.dto.PreguntaRequestDTO;
import com.example.curso.entity.Pregunta;
import com.example.curso.service.PreguntaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/preguntas")
@Tag(name = "Preguntas", description = "Operaciones para gestionar preguntas")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @Operation(summary = "Crear una nueva pregunta")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PreguntaRequestDTO preguntaRequestDTO) {
        Pregunta preguntaCreada = preguntaService.create(preguntaRequestDTO);
        return new ResponseEntity<>(preguntaCreada, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener una pregunta")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        Pregunta pregunta = preguntaService.getOne(id);
        return new ResponseEntity<>(pregunta, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todas las preguntas")
    @GetMapping
    public ResponseEntity<?> getAll(@ParameterObject Pageable pageable) {
        Page<Pregunta> preguntas = preguntaService.getAll(pageable);
        return new ResponseEntity<>(preguntas, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar una pregunta")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PreguntaRequestDTO preguntaRequestDTO) {
        Pregunta preguntaActualizada = preguntaService.update(preguntaRequestDTO, id);
        return new ResponseEntity<>(preguntaActualizada, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar una pregunta")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        preguntaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Crear varias preguntas")
    @PostMapping("/crear-preguntas")
    public ResponseEntity<?> createPreguntas(@Valid @RequestBody PreguntaRequestDTO[] preguntasRequestDTO) {
        List<Pregunta> preguntasCreadas = Arrays.stream(preguntasRequestDTO)
                .map(dto -> preguntaService.create(dto))
                .collect(Collectors.toList());
        return new ResponseEntity<>(preguntasCreadas, HttpStatus.CREATED);
    }

    @Operation(summary = "Contestar una pregunta")
    @PostMapping("/contestar/{idPregunta}/{idOpcion}")
    public ResponseEntity<?> contestar(@PathVariable Long idPregunta, @PathVariable Long idOpcion) {
        boolean contestada = preguntaService.contestar(idPregunta, idOpcion);
        return new ResponseEntity<>(contestada, HttpStatus.OK);
    }

}
