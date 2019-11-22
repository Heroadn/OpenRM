package com.requisitos.hellkaiser.rm.repository.comentario;

import com.requisitos.hellkaiser.rm.repository.filter.ComentarioFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface ComentarioRepositoryQuery {
    PageImpl filtrar(ComentarioFilter filter, Pageable pageable);
}
