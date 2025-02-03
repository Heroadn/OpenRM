package com.requisitos.hellkaiser.rm.repository.iteracao;

import com.requisitos.hellkaiser.rm.model.Iteracao;
import com.requisitos.hellkaiser.rm.repository.filter.IteracaoFilter;
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

public class IteracaoRepositoryImpl implements IteracaoRepositoryQuery {

    @Autowired
    EntityManager manager;

    @Override
    public PageImpl filtrar(IteracaoFilter iteracaoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Iteracao> criteria = builder.createQuery(Iteracao.class);
        Root<Iteracao> root = criteria.from(Iteracao.class);

        List<Predicate> predicates = criarRestricoes(iteracaoFilter, builder, root);
        criteria.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Iteracao> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl(query.getResultList(), pageable, total(iteracaoFilter));
    }

    private Long total(IteracaoFilter iteracaoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Iteracao> root = criteria.from(Iteracao.class);

        ArrayList<Predicate> predicates = criarRestricoes(iteracaoFilter, builder, root);
        criteria.where(predicates.toArray(new Predicate[]{}));

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Iteracao> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalDeResguistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDa = paginaAtual * totalDeResguistrosPorPagina;

        query.setFirstResult(primeiroRegistroDa);
        query.setMaxResults(totalDeResguistrosPorPagina);
    }

    private ArrayList<Predicate> criarRestricoes(IteracaoFilter iteracaoFilter, CriteriaBuilder builder, Root<Iteracao> root){
        ArrayList<Predicate> predicates= new ArrayList<>();

        if (!StringUtils.isEmpty(iteracaoFilter.nome)) {
            predicates.add(
                    builder.equal(root.get("nome"),iteracaoFilter.nome));
        }

        if (!StringUtils.isEmpty(iteracaoFilter.concluido)) {
            predicates.add(
                    builder.equal(root.get("concluido"),iteracaoFilter.concluido));
        }

        return predicates;
    }



}
