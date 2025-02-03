package com.requisitos.hellkaiser.rm.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.Link;

import java.security.Principal;

public interface RestServiceInterface<Model, Repository extends JpaRepository, Filter> {

    Page<Model> filter(Filter filter, Pageable pageable);

    Model get(Long id);

    Model save(Model resource, Principal auth);

    Model update(Model resource, Long id, String[] ignoredProperties, Principal auth);

    Model delete(Long id, Principal auth);

    Link generateSelfLink(Long id,Class<RestControllerInterface> controller);
}