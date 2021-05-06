package com.requisitos.hellkaiser.rm.repository.projeto;

import com.requisitos.hellkaiser.rm.repository.filter.ProjetoFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface ProjetoRepositoryQuery {
    PageImpl filtrar(ProjetoFilter filter, Pageable pageable);
}
