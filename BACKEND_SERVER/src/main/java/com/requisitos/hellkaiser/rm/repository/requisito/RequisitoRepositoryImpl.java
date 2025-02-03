package com.requisitos.hellkaiser.rm.repository.requisito;

import com.requisitos.hellkaiser.rm.model.*;
import com.requisitos.hellkaiser.rm.repository.filter.RequisitoFilter;
import com.requisitos.hellkaiser.rm.repository.filter.UsuarioFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RequisitoRepositoryImpl implements RequisitoRepositoryQuery {

    @Autowired
    EntityManager manager;

    @Override
    public PageImpl filtrar(RequisitoFilter requisitoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Requisito> criteria = builder.createQuery(Requisito.class);
        Root<Requisito> root = criteria.from(Requisito.class);

        List<Predicate> predicates = criarRestricoes(requisitoFilter, builder, root);
        criteria.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Requisito> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl(query.getResultList(), pageable, total(requisitoFilter));
    }

    private Long total(RequisitoFilter requisitoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Requisito> root = criteria.from(Requisito.class);

        ArrayList<Predicate> predicates = criarRestricoes(requisitoFilter, builder, root);
        criteria.where(predicates.toArray(new Predicate[]{}));

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Requisito> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalDeResguistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDa = paginaAtual * totalDeResguistrosPorPagina;

        query.setFirstResult(primeiroRegistroDa);
        query.setMaxResults(totalDeResguistrosPorPagina);
    }

    private ArrayList<Predicate> criarRestricoes(RequisitoFilter requisitoFilter, CriteriaBuilder builder, Root<Requisito> root){
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
