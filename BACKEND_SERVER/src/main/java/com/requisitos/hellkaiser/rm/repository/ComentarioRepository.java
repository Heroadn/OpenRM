package com.requisitos.hellkaiser.rm.repository;

import com.requisitos.hellkaiser.rm.model.Comentario;
import com.requisitos.hellkaiser.rm.repository.comentario.ComentarioRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ComentarioRepository extends JpaRepository<Comentario, Serializable>, ComentarioRepositoryQuery {
}
