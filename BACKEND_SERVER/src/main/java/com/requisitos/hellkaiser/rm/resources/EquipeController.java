package com.requisitos.hellkaiser.rm.resources;

import com.requisitos.hellkaiser.rm.generic.GenericRestController;
import com.requisitos.hellkaiser.rm.generic.RestControllerInterface;
import com.requisitos.hellkaiser.rm.model.Equipe;
import com.requisitos.hellkaiser.rm.model.Iteracao;
import com.requisitos.hellkaiser.rm.model.Usuario;
import com.requisitos.hellkaiser.rm.repository.EquipeRepository;
import com.requisitos.hellkaiser.rm.repository.IteracaoRepository;
import com.requisitos.hellkaiser.rm.repository.filter.EquipeFilter;
import com.requisitos.hellkaiser.rm.repository.filter.IteracaoFilter;
import com.requisitos.hellkaiser.rm.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/equipe" , produces = "application/hal+json")
public class EquipeController extends GenericRestController<Equipe, EquipeFilter, EquipeRepository, EquipeService> {
    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private EquipeService equipeService;

    /*Metodo responsavel por adicionar a equipe ao projeto*/
    @PostMapping(value = "/{id_equipe}")
    ResponseEntity<Usuario> adicionarUsuario(@PathVariable final long id_equipe, @RequestBody Usuario usuario)
    {
        Usuario fromDb = equipeService.adicionarUsuario(id_equipe,usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(fromDb);
    }

}
