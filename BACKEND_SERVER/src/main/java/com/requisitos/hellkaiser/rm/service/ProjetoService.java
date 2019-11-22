package com.requisitos.hellkaiser.rm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.requisitos.hellkaiser.rm.generic.BasicRestService;
import com.requisitos.hellkaiser.rm.model.*;
import com.requisitos.hellkaiser.rm.repository.*;
import com.requisitos.hellkaiser.rm.repository.filter.ProjetoFilter;
import com.requisitos.hellkaiser.rm.resources.ProjetoController;
import com.requisitos.hellkaiser.rm.security.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.Long.parseLong;
import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Service
public class ProjetoService extends BasicRestService<Projeto, ProjetoRepository, ProjetoFilter> {
    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private RequisitoRepository requisitoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IteracaoRepository iteracaoRepository;

    @Autowired
    private PostRepository postRepository;

    public Page<Projeto> filter(ProjetoFilter filter, Pageable pageable){
        Page<Projeto> list = projetoRepository.filtrar(filter,pageable);
        return list;
    }

    //adicionarIteracao
    @Override
    public Projeto save(Projeto projeto, Authentication auth){
       Projeto fromDb = projetoRepository.save(projeto);
       Usuario usuario = usuarioRepository.findById(getTokenFromLoggedUser(auth).id_usuario).get();

       List<Iteracao> iteracoes = generateIteracao("Andamento","Desenvolvimento","Conclusao");
       iteracoes.forEach( iteracao -> { adicionarIteracao(fromDb.getID(),iteracao); });
       Equipe equipe = generateEquipe("Default",fromDb,usuario);
       equipeRepository.save(equipe);
       return fromDb;
    }

    /*OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) auth.getDetails();
        Token token = decodedDetailsToToken((Map<String, Object>) oauthDetails.getDecodedDetails());

        Comentario fromDb = postService.adicionarComentario(id_post,token.id_usuario, comentario);*/

    public List<Iteracao> generateIteracao(String... names){
        List<Iteracao> array = new ArrayList<>();

        for(String n : names){
            Iteracao iteracao = new Iteracao();
            iteracao.setNome(n);
            array.add(iteracao);
        }

        return array;
    }

    public Equipe generateEquipe(String nome,Projeto projeto,Usuario usuario){
        Equipe equipe = new Equipe();
        equipe.setNome(nome);
        equipe.setProjeto(projeto);
        equipe.setUsuario(usuario);
        return equipe;
    }

    public Projeto buscarProjetoPorId(long id){ return get(id); }

    public Projeto projetoAtualizar(Projeto projeto, long id){ return update(projeto, id,"id_projeto","data_criacao"); }

    public Link generateLink(long id){ return linkTo(methodOn(ProjetoController.class).findById(id)).withSelfRel(); }

    public Equipe adicionarEquipe(long id_projeto, Equipe equipe){
        /*atualizando lado do projeto*/
        Projeto projeto = buscarProjetoPorId(id_projeto);
        projeto.addEquipe(equipe);
        Projeto fromdb = projetoRepository.save(projeto);

        /*atualizando lado da equipe*/
        equipe.setProjeto(projeto);
        equipeRepository.save(equipe);
        return equipe;
    }

    public Requisito adicionarRequisito(long id_projeto, Requisito requisito){
        /*atualizando lado do projeto*/
        Projeto projeto = buscarProjetoPorId(id_projeto);
        projeto.addRequisito(requisito);
        Projeto fromdb = projetoRepository.save(projeto);

        /*atualizando lado da equipe*/
        requisito.setProjeto(projeto);
        requisitoRepository.save(requisito);
        return requisito;
    }

    public Iteracao adicionarIteracao(long id_projeto, Iteracao iteracao){
        Projeto projeto = get(id_projeto);
        iteracao.setProjeto(projeto);
        return iteracaoRepository.save(iteracao);
    }

    public Post adicionarPost(long id_projeto, long id_usuario, Post post) {
        Optional<Usuario> usuario = usuarioRepository.findById(id_usuario);

        /*Caso o usuario exista*/
        usuario.ifPresent(u -> {
            /*Adicionando post ao projeto*/
            Projeto projeto = buscarProjetoPorId(id_projeto);
            post.setProjeto(projeto);
            post.setUsuario(u);
            postRepository.save(post);

            projeto.addPost(post);
            projetoRepository.save(projeto);
        });
        return post;
    }

    public Token getTokenFromLoggedUser(Authentication auth){
        OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) auth.getDetails();
        Token token = decodedDetailsToToken((Map<String, Object>) oauthDetails.getDecodedDetails());
        return token;
    }

    public Token decodedDetailsToToken(Map<String, Object> oauthDetails){
        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final Token token = mapper.convertValue(oauthDetails, Token.class);
        return token;
    }
}
