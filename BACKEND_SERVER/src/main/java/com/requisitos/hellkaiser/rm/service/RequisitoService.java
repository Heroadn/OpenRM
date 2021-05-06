package com.requisitos.hellkaiser.rm.service;

import com.requisitos.hellkaiser.rm.generic.BasicRestService;
import com.requisitos.hellkaiser.rm.generic.GenericRestController;
import com.requisitos.hellkaiser.rm.model.Projeto;
import com.requisitos.hellkaiser.rm.model.Requisito;
import com.requisitos.hellkaiser.rm.model.Usuario;
import com.requisitos.hellkaiser.rm.repository.ProjetoRepository;
import com.requisitos.hellkaiser.rm.repository.RequisitoRepository;
import com.requisitos.hellkaiser.rm.repository.filter.RequisitoFilter;
import com.requisitos.hellkaiser.rm.repository.filter.UsuarioFilter;
import com.requisitos.hellkaiser.rm.resources.RequisitoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Service
public class RequisitoService extends BasicRestService<Requisito, RequisitoRepository, RequisitoFilter> {
    @Autowired
    private RequisitoRepository requisitoRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public Page<Requisito> filter(RequisitoFilter filter, Pageable pageable){
        Page<Requisito> list = requisitoRepository.filtrar(filter,pageable);
        return list;
    }

    public Projeto buscarProjetoPorId(long id){
        Optional<Projeto> projeto = projetoRepository.findById(id);
        projeto.orElseThrow(() -> new EmptyResultDataAccessException(1));
        return projeto.get();
    }

    public Requisito adicionarRequisitoParaProjeto(long id_projeto, Requisito requisito){
        /*atualizando lado do projeto*/
        Projeto projeto = buscarProjetoPorId(id_projeto);
        projeto.addRequisito(requisito);
        Projeto fromdb = projetoRepository.save(projeto);

        /*atualizando lado da equipe*/
        requisito.setProjeto(projeto);
        requisitoRepository.save(requisito);
        return requisito;
    }
}
