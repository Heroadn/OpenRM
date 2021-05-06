package com.requisitos.hellkaiser.rm.repository;

import com.requisitos.hellkaiser.rm.model.Requisito;
import com.requisitos.hellkaiser.rm.repository.requisito.RequisitoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface RequisitoRepository extends JpaRepository<Requisito, Serializable>, RequisitoRepositoryQuery {
}
