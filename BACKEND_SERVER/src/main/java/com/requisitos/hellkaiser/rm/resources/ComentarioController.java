package com.requisitos.hellkaiser.rm.resources;

import com.requisitos.hellkaiser.rm.generic.GenericRestController;
import com.requisitos.hellkaiser.rm.generic.RestControllerInterface;
import com.requisitos.hellkaiser.rm.model.Comentario;
import com.requisitos.hellkaiser.rm.model.Equipe;
import com.requisitos.hellkaiser.rm.repository.ComentarioRepository;
import com.requisitos.hellkaiser.rm.repository.EquipeRepository;
import com.requisitos.hellkaiser.rm.repository.filter.ComentarioFilter;
import com.requisitos.hellkaiser.rm.repository.filter.EquipeFilter;
import com.requisitos.hellkaiser.rm.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/comentario" , produces = "application/hal+json")
public class ComentarioController extends GenericRestController<Comentario, ComentarioFilter, ComentarioRepository, ComentarioService> {

}
