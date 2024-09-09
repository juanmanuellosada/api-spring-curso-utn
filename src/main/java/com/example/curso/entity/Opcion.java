package com.example.curso.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "opcion")
@Data
public class Opcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String enunciado;
    private Boolean esCorrecta;

    @ManyToOne
    @JoinColumn(name = "pregunta_id")
    @JsonBackReference
    private Pregunta pregunta;
}
