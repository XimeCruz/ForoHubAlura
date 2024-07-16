package com.one_oracle.foro_alura.repository;

import com.one_oracle.foro_alura.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}

