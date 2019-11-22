package com.requisitos.hellkaiser.rm.repository.post;

import com.requisitos.hellkaiser.rm.model.Post;
import com.requisitos.hellkaiser.rm.model.Requisito;
import com.requisitos.hellkaiser.rm.repository.filter.PostFilter;
import com.requisitos.hellkaiser.rm.repository.filter.RequisitoFilter;
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

public class PostRepositoryImpl implements PostRepositoryQuery {

    @Autowired
    EntityManager manager;

    @Override
    public PageImpl filtrar(PostFilter postfilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Post> criteria = builder.createQuery(Post.class);
        Root<Post> root = criteria.from(Post.class);

        List<Predicate> predicates = criarRestricoes(postfilter, builder, root);
        criteria.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Post> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl(query.getResultList(), pageable, total(postfilter));
    }

    private Long total(PostFilter postfilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Post> root = criteria.from(Post.class);

        ArrayList<Predicate> predicates = criarRestricoes(postfilter, builder, root);
        criteria.where(predicates.toArray(new Predicate[]{}));

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Post> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalDeResguistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDa = paginaAtual * totalDeResguistrosPorPagina;

        query.setFirstResult(primeiroRegistroDa);
        query.setMaxResults(totalDeResguistrosPorPagina);
    }

    private ArrayList<Predicate> criarRestricoes(PostFilter postfilter, CriteriaBuilder builder, Root<Post> root){
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
