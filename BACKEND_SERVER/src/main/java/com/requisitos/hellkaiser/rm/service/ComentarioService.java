package com.requisitos.hellkaiser.rm.service;

import com.requisitos.hellkaiser.rm.generic.BasicRestService;
import com.requisitos.hellkaiser.rm.model.*;
import com.requisitos.hellkaiser.rm.repository.*;
import com.requisitos.hellkaiser.rm.repository.filter.ComentarioFilter;
import com.requisitos.hellkaiser.rm.resources.ComentarioController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService extends BasicRestService<Comentario, ComentarioRepository, ComentarioFilter> {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Page<Comentario> filter(ComentarioFilter filter, Pageable pageable){
        Page<Comentario> list = comentarioRepository.filtrar(filter,pageable);
        return list;
    }
}