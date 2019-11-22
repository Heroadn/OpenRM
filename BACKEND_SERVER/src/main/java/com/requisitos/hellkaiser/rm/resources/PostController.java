package com.requisitos.hellkaiser.rm.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.requisitos.hellkaiser.rm.generic.GenericRestController;
import com.requisitos.hellkaiser.rm.model.Comentario;
import com.requisitos.hellkaiser.rm.model.Post;
import com.requisitos.hellkaiser.rm.repository.PostRepository;
import com.requisitos.hellkaiser.rm.repository.filter.PostFilter;
import com.requisitos.hellkaiser.rm.security.Token;
import com.requisitos.hellkaiser.rm.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/post" , produces = "application/hal+json")
public class PostController extends GenericRestController<Post, PostFilter, PostRepository, PostService> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    /*Metodo responsavel por adicioar a equipe ao projeto*/
    @PostMapping(value = "/{id_post}/add-comentario")
    ResponseEntity<Comentario> adicionarComentario(Authentication auth, @PathVariable final long id_post, @RequestBody Comentario comentario) {
        OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) auth.getDetails();
        Token token = decodedDetailsToToken((Map<String, Object>) oauthDetails.getDecodedDetails());

        Comentario fromDb = postService.adicionarComentario(id_post,token.id_usuario, comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(fromDb);
    }

    public Token decodedDetailsToToken(Map<String, Object> oauthDetails){
        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final Token token = mapper.convertValue(oauthDetails, Token.class);
        return token;
    }

}
