package com.requisitos.hellkaiser.rm.resources;

import com.requisitos.hellkaiser.rm.generic.GenericRestController;
import com.requisitos.hellkaiser.rm.model.*;
import com.requisitos.hellkaiser.rm.repository.IteracaoRepository;
import com.requisitos.hellkaiser.rm.repository.filter.IteracaoFilter;
import com.requisitos.hellkaiser.rm.service.IteracaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/iteracao" , produces = "application/hal+json")
@Api("Operações de gerenciamento de iteracoes")
public class IteracaoController extends GenericRestController<Iteracao, IteracaoFilter, IteracaoRepository, IteracaoService> {
    @Autowired
    private IteracaoRepository iteracaoRepository;

    @Autowired
    private IteracaoService iteracaoService;

    /*Metodo responsavel por adicioar a equipe ao projeto*/
    @ApiOperation(value = "Adicionar um requisto a uma iteracao")
    @PostMapping(value = "/{id_iteracao}")
    ResponseEntity<Requisito> adicionarRequisito(@PathVariable final long id_iteracao,@RequestBody Requisito requisito)
    {
        Requisito fromDb = iteracaoService.adicionarRequisitoParaIteracao(id_iteracao,requisito);
        return ResponseEntity.status(HttpStatus.CREATED).body(fromDb);
    }

}
