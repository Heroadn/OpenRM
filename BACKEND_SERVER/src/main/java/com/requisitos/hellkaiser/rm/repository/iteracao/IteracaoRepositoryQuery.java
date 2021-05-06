package com.requisitos.hellkaiser.rm.repository.iteracao;

import com.requisitos.hellkaiser.rm.repository.filter.IteracaoFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface IteracaoRepositoryQuery{
    PageImpl filtrar(IteracaoFilter filter, Pageable pageable);
}
