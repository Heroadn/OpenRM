package com.requisitos.hellkaiser.rm.repository.usuario;

import com.requisitos.hellkaiser.rm.repository.filter.UsuarioFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface UsuarioRepositoryQuery {
    PageImpl filtrar(UsuarioFilter filter, Pageable pageable);
}
