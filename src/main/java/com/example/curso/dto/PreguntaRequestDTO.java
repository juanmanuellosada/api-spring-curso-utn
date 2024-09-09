package com.example.curso.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PreguntaRequestDTO {

    @NotEmpty
    @Size(min = 10, max = 100, message = "El enunciado debe tener entre 10 y 100 caracteres")
    private String enunciado;

    @NotEmpty
    @Size(min = 3, max = 3, message = "Debe tener exactamente 3 opciones")
    private OpcionRequestDTO[] opciones;

}
