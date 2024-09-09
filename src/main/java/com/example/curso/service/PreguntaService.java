package com.example.curso.service;

import com.example.curso.dto.PreguntaRequestDTO;
import com.example.curso.entity.Pregunta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PreguntaService {

    public Pregunta create(PreguntaRequestDTO preguntaRequestDTO);

    public Pregunta getOne(Long id);

    public Page<Pregunta> getAll(Pageable pageable);

    public Pregunta update(PreguntaRequestDTO preguntaRequestDTO, Long id);

    public void delete(Long id);

    public Boolean contestar(Long idPregunta, Long idOpcion);
}
