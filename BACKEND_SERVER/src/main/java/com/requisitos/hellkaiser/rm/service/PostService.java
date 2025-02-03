package com.requisitos.hellkaiser.rm.service;

import com.requisitos.hellkaiser.rm.generic.BasicRestService;
import com.requisitos.hellkaiser.rm.model.*;
import com.requisitos.hellkaiser.rm.repository.*;
import com.requisitos.hellkaiser.rm.repository.filter.PostFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PostService extends BasicRestService<Post, PostRepository, PostFilter> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Page<Post> filter(PostFilter filter, Pageable pageable){
        Page<Post> list = postRepository.filtrar(filter,pageable);
        return list;
    }

    /* Busca o usuario pelo id */
    public Post buscarPostPorId(long id){ return get(id); }

    public Comentario adicionarComentario(long id_post, long id_usuario, Comentario comentario) {
        Optional<Usuario> usuario = usuarioRepository.findById(id_usuario);

        /*Caso o usuario exista*/
        usuario.ifPresent(u -> {
            /*Buscando post*/
            Post post = buscarPostPorId(id_post);

            /*Adicionando comentario ao post*/
            comentario.setUsuario(u);
            comentario.setPost(post);

            comentarioRepository.save(comentario);
        });
        return comentario;
    }
}
