package com.requisitos.hellkaiser.rm.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.Link;
import org.springframework.security.core.Authentication;

public interface RestServiceInterface<Model, Repository extends JpaRepository, Filter> {

    Page<Model> filter(Filter filter, Pageable pageable);

    Model get(Long id);

    Model save(Model resource, Authentication auth);

    Model update(Model resource, Long id,String... ignoredProperties);

    Model delete(Long id);

    Link generateSelfLink(Long id,Class<RestControllerInterface> controller);
}
