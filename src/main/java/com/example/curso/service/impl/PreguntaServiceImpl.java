package com.example.curso.service.impl;

import com.example.curso.dto.PreguntaRequestDTO;
import com.example.curso.entity.Opcion;
import com.example.curso.entity.Pregunta;
import com.example.curso.exceptions.custom.ResourceNotFoundException;
import com.example.curso.repository.PreguntaRepository;
import com.example.curso.service.OpcionService;
import com.example.curso.service.PreguntaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreguntaServiceImpl implements PreguntaService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private OpcionService opcionService;


    @Override
    public Pregunta create(PreguntaRequestDTO preguntaRequestDTO) {
        Pregunta preguntaACrear = modelMapper.map(preguntaRequestDTO, Pregunta.class);

        List<Opcion> opcionesCreadas = Arrays.stream(preguntaRequestDTO.getOpciones())
                .map(dto -> {
                    Opcion opcion = modelMapper.map(dto, Opcion.class);
                    opcion.setPregunta(preguntaACrear);
                    return opcion;
                }).collect(Collectors.toList());
        preguntaACrear.setOpciones(opcionesCreadas);

        return preguntaRepository.save(preguntaACrear);
    }

    @Override
    public Pregunta getOne(Long id) {
        return preguntaRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Pregunta", "id", id));
    }

    @Override
    public Page<Pregunta> getAll(Pageable pageable) {
        return preguntaRepository.findAll(pageable);
    }

    @Override
    public Pregunta update(PreguntaRequestDTO preguntaRequestDTO, Long id) {
        Pregunta preguntaAActualizar = preguntaRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Pregunta", "id", id));

        preguntaAActualizar.setEnunciado(preguntaRequestDTO.getEnunciado());

        preguntaAActualizar.setOpciones(opcionService.crearOpciones(preguntaRequestDTO.getOpciones()));
        return preguntaRepository.save(preguntaAActualizar);
    }

    @Override
    public void delete(Long id) {
        Pregunta preguntaAEliminar = preguntaRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Pregunta", "id", id));
        preguntaRepository.delete(preguntaAEliminar);
    }

    @Override
    public Boolean contestar(Long idPregunta, Long idOpcion) {
        Pregunta pregunta = getOne(idPregunta);
        return pregunta.getOpciones().stream()
                .anyMatch(opcion -> opcion.getId().equals(idOpcion) && opcion.getEsCorrecta());
    }
}
