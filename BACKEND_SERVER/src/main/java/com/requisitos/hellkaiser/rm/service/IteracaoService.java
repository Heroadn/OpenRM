package com.requisitos.hellkaiser.rm.service;

import com.requisitos.hellkaiser.rm.generic.BasicRestService;
import com.requisitos.hellkaiser.rm.model.Iteracao;
import com.requisitos.hellkaiser.rm.model.Post;
import com.requisitos.hellkaiser.rm.model.Requisito;
import com.requisitos.hellkaiser.rm.repository.IteracaoRepository;
import com.requisitos.hellkaiser.rm.repository.RequisitoRepository;
import com.requisitos.hellkaiser.rm.repository.filter.IteracaoFilter;
import com.requisitos.hellkaiser.rm.repository.filter.PostFilter;
import com.requisitos.hellkaiser.rm.resources.IteracaoController;
import com.requisitos.hellkaiser.rm.resources.PostController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

@Service
public class IteracaoService extends BasicRestService<Iteracao, IteracaoRepository, IteracaoFilter> {
    @Autowired
    private IteracaoRepository iteracaoRepository;

    @Autowired
    private RequisitoRepository requisitoRepository;

    public Page<Iteracao> filter(IteracaoFilter filter, Pageable pageable){
        Page<Iteracao> list = iteracaoRepository.filtrar(filter,pageable);
        return list;
    }

    public Iteracao buscarIteracaoPorId(long id){ return  get(id); }

    public Requisito adicionarRequisitoParaIteracao(long id_iteracao, Requisito requisito){
        /*buscando iteracao informada*/
        Iteracao iteracao = buscarIteracaoPorId(id_iteracao);
        /*Adicionando requisito a lista de iteracao*/
        iteracao.addRequisito(requisito);

        /*Adicionando iteracao ao lado do requisito*/
        requisito.setIteracao(iteracao);
        return requisitoRepository.save(requisito);
    }

}
