package com.example.curso.controller;

import com.example.curso.dto.OpcionRequestDTO;
import com.example.curso.entity.Opcion;
import com.example.curso.service.OpcionService;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/opciones")
@Tag(name = "Opciones", description = "Operaciones para gestionar opciones")
public class OptionController {

    @Autowired
    private OpcionService opcionService;

    @Operation(summary = "Crear una nueva opción")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody OpcionRequestDTO opcionRequestDTO) {
        Opcion opcionCreada = opcionService.create(opcionRequestDTO);
        return new ResponseEntity<>(opcionCreada, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener una opción")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        Opcion opcion = opcionService.getOne(id);
        return new ResponseEntity<>(opcion, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todas las opciones")
    @GetMapping
    public ResponseEntity<?> getAll(@ParameterObject Pageable pageable) {
        Page<Opcion> opciones = opcionService.getAll(pageable);
        return new ResponseEntity<>(opciones, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar una opción")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody OpcionRequestDTO opcionRequestDTO) {
        Opcion opcionActualizada = opcionService.update(id, opcionRequestDTO);
        return new ResponseEntity<>(opcionActualizada, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar una opción")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        opcionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Crear varias opciones")
    @PostMapping("/crear-opciones")
    public ResponseEntity<?> createOpciones(@Valid @RequestBody OpcionRequestDTO[] opcionesRequestDTO) {
        List<Opcion> opcionesCreadas = opcionService.crearOpciones(opcionesRequestDTO);
        return new ResponseEntity<>(opcionesCreadas, HttpStatus.CREATED);
    }
}
