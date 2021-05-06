package com.requisitos.hellkaiser.rm.repository.comentario;

import com.requisitos.hellkaiser.rm.model.Comentario;
import com.requisitos.hellkaiser.rm.repository.filter.ComentarioFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ComentarioRepositoryImpl implements ComentarioRepositoryQuery {

    @Autowired
    EntityManager manager;

    @Override
    public PageImpl filtrar(ComentarioFilter comentarioFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Comentario> criteria = builder.createQuery(Comentario.class);
        Root<Comentario> root = criteria.from(Comentario.class);

        List<Predicate> predicates = criarRestricoes(comentarioFilter, builder, root);
        criteria.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Comentario> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl(query.getResultList(), pageable, total(comentarioFilter));
    }

    private Long total(ComentarioFilter comentarioFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Comentario> root = criteria.from(Comentario.class);

        ArrayList<Predicate> predicates = criarRestricoes(comentarioFilter, builder, root);
        criteria.where(predicates.toArray(new Predicate[]{}));

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Comentario> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalDeResguistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDa = paginaAtual * totalDeResguistrosPorPagina;

        query.setFirstResult(primeiroRegistroDa);
        query.setMaxResults(totalDeResguistrosPorPagina);
    }

    private ArrayList<Predicate> criarRestricoes(ComentarioFilter comentarioFilter, CriteriaBuilder builder, Root<Comentario> root){
        ArrayList<Predicate> predicates= new ArrayList<>();

        /*
        if (!StringUtils.isEmpty(requisitoFilter.nome)) {
            predicates.add(
                    builder.equal(root.get(Requisito_.NOME),requisitoFilter.nome));
        }

        if (!StringUtils.isEmpty(requisitoFilter.prioridade)) {
            predicates.add(
                    builder.equal(root.get(Requisito_.PRIORIDADE),requisitoFilter.prioridade));
        }*/

        return predicates;
    }



}
