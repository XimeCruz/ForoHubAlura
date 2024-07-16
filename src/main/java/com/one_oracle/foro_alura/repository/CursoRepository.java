package com.one_oracle.foro_alura.repository;

import com.one_oracle.foro_alura.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {}
