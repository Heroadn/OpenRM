package com.requisitos.hellkaiser.rm.repository.equipe;

import com.requisitos.hellkaiser.rm.repository.filter.EquipeFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface EquipeRepositoryQuery{
    PageImpl filtrar(EquipeFilter filter, Pageable pageable);
}
