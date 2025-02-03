package com.requisitos.hellkaiser.rm.repository.projeto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.PageImpl;
import com.requisitos.hellkaiser.rm.model.Projeto;
import com.requisitos.hellkaiser.rm.repository.filter.ProjetoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProjetoRepositoryImpl implements ProjetoRepositoryQuery {

    @Autowired
    EntityManager manager;

    @Override
    public PageImpl filtrar(ProjetoFilter projetoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Projeto> criteria = builder.createQuery(Projeto.class);
        Root<Projeto> root = criteria.from(Projeto.class);

        List<Predicate> predicates = criarRestricoes(projetoFilter, builder, root);
        criteria.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Projeto> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl(query.getResultList(), pageable, total(projetoFilter));
    }

    private Long total(ProjetoFilter projetoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Projeto> root = criteria.from(Projeto.class);

        ArrayList<Predicate> predicates = criarRestricoes(projetoFilter, builder, root);
        criteria.where(predicates.toArray(new Predicate[]{}));

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Projeto> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalDeResguistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDa = paginaAtual * totalDeResguistrosPorPagina;

        query.setFirstResult(primeiroRegistroDa);
        query.setMaxResults(totalDeResguistrosPorPagina);
    }

    private ArrayList<Predicate> criarRestricoes(ProjetoFilter projetoFilter, CriteriaBuilder builder, Root<Projeto> root){
        ArrayList<Predicate> predicates= new ArrayList<Predicate>();

        if (!StringUtils.isEmpty(projetoFilter.nome)) {
            //Projeto_.nome
            predicates.add(
                    builder.equal(root.get("nome"),projetoFilter.nome));
        }

        return predicates;
    }
}
