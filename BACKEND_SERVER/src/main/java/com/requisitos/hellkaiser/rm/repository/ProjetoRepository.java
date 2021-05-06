package com.requisitos.hellkaiser.rm.repository;

import com.requisitos.hellkaiser.rm.model.Equipe;
import com.requisitos.hellkaiser.rm.model.Projeto;
import com.requisitos.hellkaiser.rm.repository.projeto.ProjetoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Serializable>, ProjetoRepositoryQuery {
    List<Projeto> findByEquipesIn(List<Equipe> equipes);
}
