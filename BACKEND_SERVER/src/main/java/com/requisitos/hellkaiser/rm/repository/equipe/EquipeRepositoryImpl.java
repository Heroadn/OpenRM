package com.requisitos.hellkaiser.rm.repository.equipe;

import com.requisitos.hellkaiser.rm.model.Equipe;
import com.requisitos.hellkaiser.rm.model.Equipe_;
import com.requisitos.hellkaiser.rm.repository.filter.EquipeFilter;
import org.springframework.data.domain.PageImpl;
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

public class EquipeRepositoryImpl implements EquipeRepositoryQuery {
    @Autowired
    EntityManager manager;

    @Override
    public PageImpl filtrar(EquipeFilter equipeFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Equipe> criteria = builder.createQuery(Equipe.class);
        Root<Equipe> root = criteria.from(Equipe.class);

        List<Predicate> predicates = criarRestricoes(equipeFilter, builder, root);
        criteria.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Equipe> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl(query.getResultList(), pageable, total(equipeFilter));
    }

    private Long total(EquipeFilter equipeFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Equipe> root = criteria.from(Equipe.class);

        ArrayList<Predicate> predicates = criarRestricoes(equipeFilter, builder, root);
        criteria.where(predicates.toArray(new Predicate[]{}));

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Equipe> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalDeResguistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDa = paginaAtual * totalDeResguistrosPorPagina;

        query.setFirstResult(primeiroRegistroDa);
        query.setMaxResults(totalDeResguistrosPorPagina);
    }

    public ArrayList<Predicate> criarRestricoes(EquipeFilter equipeFilter, CriteriaBuilder builder, Root<Equipe> root){
        ArrayList<Predicate> predicates= new ArrayList<>();

        if (!StringUtils.isEmpty(equipeFilter.nome)) {
            predicates.add(
                    builder.equal(root.get(Equipe_.NOME),equipeFilter.nome));
        }

        if (!StringUtils.isEmpty(equipeFilter.projeto)) {
            predicates.add(
                    builder.equal(root.get(Equipe_.PROJETO),equipeFilter.projeto));
        }

        return predicates;
    }
}
