package com.requisitos.hellkaiser.rm.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.requisitos.hellkaiser.rm.generic.GenericRestController;
import com.requisitos.hellkaiser.rm.model.*;
import com.requisitos.hellkaiser.rm.repository.ProjetoRepository;
import com.requisitos.hellkaiser.rm.repository.filter.ProjetoFilter;
import com.requisitos.hellkaiser.rm.security.Token;
import com.requisitos.hellkaiser.rm.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/projeto", produces = "application/hal+json")
public class ProjetoController extends GenericRestController<Projeto, ProjetoFilter, ProjetoRepository, ProjetoService> {
    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ProjetoService projetoService;

    /*Metodo responsavel por adicioar a equipe ao projeto*/
    @PostMapping(value = "/{id_projeto}/add-equipe")
    ResponseEntity<Equipe> adicionarEquipe(@PathVariable final long id_projeto, @RequestBody Equipe equipe)
    {
        Equipe fromDb = projetoService.adicionarEquipe(id_projeto,equipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(fromDb);
    }

    /*Metodo responsavel por adicionar requisito a projeto*/
    @PostMapping(value = "/{id_projeto}/add-requisito")
    //@ApiOperation(value = "Adicionar um requisto a um projeto")
    ResponseEntity<Requisito> adicionarProjeto(@PathVariable final long id_projeto, @RequestBody Requisito requisito)
    {
        Requisito fromDb = projetoService.adicionarRequisito(id_projeto,requisito);
        return ResponseEntity.status(HttpStatus.CREATED).body(fromDb);
    }

    /*Metodo responsavel por adicionar iteracao a projeto*/
    @PostMapping(value = "/{id_tarefa}/add-iteracao")
    //@ApiOperation(value = "Adiciona uma iteracao ao projeto")
    ResponseEntity<Iteracao> adicionarIteracao(@PathVariable final long id_projeto, @RequestBody Iteracao iteracao)
    {
        Iteracao fromDb = projetoService.adicionarIteracao(id_projeto,iteracao);
        return ResponseEntity.status(HttpStatus.CREATED).body(fromDb);
    }

    /*Metodo responsavel por adicionar post a projeto*/
    @PostMapping(value = "/{id_projeto}/add-post")
    //@ApiOperation(value = "Adicionar uma iteracao ao projeto")
    ResponseEntity<Post> adicionarPost(Authentication auth, @PathVariable final long id_projeto, @RequestBody Post post)
    {
        //OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) auth.getDetails();
        //Token token = decodedDetailsToToken((Map<String, Object>) oauthDetails.getDecodedDetails());
        Token token = null;

        Post fromDb = projetoService.adicionarPost(id_projeto,token.id_usuario, post);
        return ResponseEntity.status(HttpStatus.CREATED).body(fromDb);
    }

    public Token decodedDetailsToToken(Map<String, Object> oauthDetails)
    {
        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final Token token = mapper.convertValue(oauthDetails, Token.class);
        return token;
    }

    private void simulateSlowService()
    {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
