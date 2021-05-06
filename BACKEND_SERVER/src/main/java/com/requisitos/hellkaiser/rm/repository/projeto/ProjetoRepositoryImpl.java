package com.requisitos.hellkaiser.rm.repository.projeto;

import org.springframework.data.domain.PageImpl;
import com.requisitos.hellkaiser.rm.model.Projeto;
import com.requisitos.hellkaiser.rm.model.Projeto_;
import com.requisitos.hellkaiser.rm.repository.filter.ProjetoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
            predicates.add(
                    builder.equal(root.get(Projeto_.nome),projetoFilter.nome));
        }

        return predicates;
    }
}
