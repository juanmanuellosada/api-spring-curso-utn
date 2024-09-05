package com.example.curso.services;

import com.example.curso.controller.dto.PreguntaRequestDTO;
import com.example.curso.entity.Pregunta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PreguntaService {

    public Pregunta create(PreguntaRequestDTO aeropuertoRequestDTO);

    public Pregunta getOne(Long id);

    public Page<Pregunta> getAll(Pageable pageable);

    public Pregunta update(PreguntaRequestDTO aeropuertoRequestDTO, Long id);

    public void delete(Long id);

}
