package com.requisitos.hellkaiser.rm.repository.usuario;

import com.requisitos.hellkaiser.rm.model.Usuario;
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

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {

    @Autowired
    EntityManager manager;

    @Override
    public PageImpl filtrar(UsuarioFilter usuarioFilter, Pageable pageable) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
        Root<Usuario> root = criteria.from(Usuario.class);

        List<Predicate> predicates = criarRestricoes(usuarioFilter, builder, root);
        criteria.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Usuario> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl(query.getResultList(), pageable, total(usuarioFilter));
    }

    private Long total(UsuarioFilter usuarioFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Usuario> root = criteria.from(Usuario.class);

        ArrayList<Predicate> predicates = criarRestricoes(usuarioFilter, builder, root);
        criteria.where(predicates.toArray(new Predicate[]{}));

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Usuario> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalDeResguistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDa = paginaAtual * totalDeResguistrosPorPagina;

        query.setFirstResult(primeiroRegistroDa);
        query.setMaxResults(totalDeResguistrosPorPagina);
    }

    private ArrayList<Predicate> criarRestricoes(UsuarioFilter usuarioFilter, CriteriaBuilder builder, Root<Usuario> root){
        ArrayList<Predicate> predicates= new ArrayList<>();

        if (!StringUtils.isEmpty(usuarioFilter.nome)) {
            predicates.add(
                    builder.equal(root.get("nome"),usuarioFilter.nome));
        }

        if (!StringUtils.isEmpty(usuarioFilter.email)) {
            predicates.add(
                    builder.equal(root.get("email"),usuarioFilter.email));
        }

        return predicates;
    }



}
