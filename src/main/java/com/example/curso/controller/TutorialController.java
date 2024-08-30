package com.example.curso.controller;

import com.example.curso.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso.entity.Tutorial;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tutoriales")
public class TutorialController {

    @Autowired
    public TutorialRepository tutorialRepository;

    @GetMapping
    public List<Tutorial> findAll() {
        return tutorialRepository.findAll();
    }

    @RequestMapping("/{id}")
    public Tutorial findById(@PathVariable Long id) {
        return tutorialRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe el tutorial con id: " + id));
    }

    @RequestMapping("/publicados")
    public List<Tutorial> findByPublicado() {
        return tutorialRepository.findByPublicado(true);
    }

}
