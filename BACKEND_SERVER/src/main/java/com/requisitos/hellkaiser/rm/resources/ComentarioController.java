package com.requisitos.hellkaiser.rm.resources;

import com.requisitos.hellkaiser.rm.generic.GenericRestController;
import com.requisitos.hellkaiser.rm.model.Comentario;
import com.requisitos.hellkaiser.rm.repository.ComentarioRepository;
import com.requisitos.hellkaiser.rm.repository.filter.ComentarioFilter;
import com.requisitos.hellkaiser.rm.service.ComentarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/comentario" , produces = "application/hal+json")
public class ComentarioController extends GenericRestController<Comentario, ComentarioFilter, ComentarioRepository, ComentarioService> {

}
