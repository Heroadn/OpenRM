package com.requisitos.hellkaiser.rm.repository;

import com.requisitos.hellkaiser.rm.model.Iteracao;
import com.requisitos.hellkaiser.rm.repository.iteracao.IteracaoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface IteracaoRepository extends JpaRepository<Iteracao, Serializable>, IteracaoRepositoryQuery {
}
