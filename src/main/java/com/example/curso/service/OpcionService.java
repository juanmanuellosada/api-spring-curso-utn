package com.example.curso.service;

import com.example.curso.dto.OpcionRequestDTO;
import com.example.curso.entity.Opcion;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OpcionService {

    public Opcion create(OpcionRequestDTO opcionRequestDTO);

    public Opcion getOne(Long id);

    public Page<Opcion> getAll(Pageable pageable);

    public Opcion update(Long id, OpcionRequestDTO opcionRequestDTO);

    public void delete(Long id);

    List<Opcion> crearOpciones(@NotEmpty @Size(min = 3, max = 3, message = "Debe tener exactamente 3 opciones") OpcionRequestDTO[] opciones);
}
