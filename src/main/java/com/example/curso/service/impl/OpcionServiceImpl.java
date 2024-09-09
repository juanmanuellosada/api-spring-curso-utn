package com.example.curso.service.impl;

import com.example.curso.dto.OpcionRequestDTO;
import com.example.curso.entity.Opcion;
import com.example.curso.exceptions.custom.ResourceNotFoundException;
import com.example.curso.repository.OpcionRepository;
import com.example.curso.service.OpcionService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OpcionServiceImpl implements OpcionService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OpcionRepository opcionRepository;

    @Override
    public Opcion create(OpcionRequestDTO opcionRequestDTO) {
        Opcion opcionACrear = modelMapper.map(opcionRequestDTO, Opcion.class);
        return opcionRepository.save(opcionACrear);
    }

    @Override
    public Opcion getOne(Long id) {
        return opcionRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Opcion", "id", id));
    }

    @Override
    public Page<Opcion> getAll(Pageable pageable) {
        return opcionRepository.findAll(pageable);
    }

    @Override
    public Opcion update(Long id, OpcionRequestDTO opcionRequestDTO) {
        Opcion opcionAActualizar = opcionRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Opcion", "id", id));

        opcionAActualizar.setEnunciado(opcionRequestDTO.getEnunciado());

        return opcionRepository.save(opcionAActualizar);
    }

    @Override
    public void delete(Long id) {
        Opcion opcionAEliminar = opcionRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Opcion", "id", id));
        opcionRepository.delete(opcionAEliminar);
    }

    @Override
    public List<Opcion> crearOpciones(@NotEmpty @Size(min = 3, max = 3, message = "Debe tener exactamente 3 opciones") OpcionRequestDTO[] opciones) {
        return Arrays.stream(opciones).map(opcionRequestDTO -> modelMapper.map(opcionRequestDTO, Opcion.class)).toList();
    }
}
