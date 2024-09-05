package com.example.curso.controller;

import com.example.curso.entity.Pregunta;
import com.example.curso.services.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/v1/pregunta")
public class PreguntaController {

    private PreguntaService preguntaService;
    public PreguntaController(PreguntaService preguntaService){
        this.preguntaService = preguntaService;
    }

    @GetMapping
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Long id){
        Pregunta pregunta = preguntaService.getOne(id);
        return new ResponseEntity<>(pregunta, HttpStatus.OK);
    }

}
