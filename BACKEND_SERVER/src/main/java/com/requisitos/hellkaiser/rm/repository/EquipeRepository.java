package com.requisitos.hellkaiser.rm.repository;

import com.requisitos.hellkaiser.rm.model.Equipe;
import com.requisitos.hellkaiser.rm.model.Usuario;
import com.requisitos.hellkaiser.rm.repository.equipe.EquipeRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface EquipeRepository extends JpaRepository<Equipe, Serializable>, EquipeRepositoryQuery {
}
