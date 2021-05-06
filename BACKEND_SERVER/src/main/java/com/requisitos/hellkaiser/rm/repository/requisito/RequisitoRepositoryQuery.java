package com.requisitos.hellkaiser.rm.repository.requisito;

import com.requisitos.hellkaiser.rm.repository.filter.RequisitoFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface RequisitoRepositoryQuery{
    PageImpl filtrar(RequisitoFilter filter, Pageable pageable);
}
