package com.example.curso.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pregunta")
@Data
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String enunciado;

}
