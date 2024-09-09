package com.example.curso.repository;

import com.example.curso.entity.Opcion;
import com.example.curso.entity.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
}
