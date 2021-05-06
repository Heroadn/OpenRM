package com.requisitos.hellkaiser.rm.generic;

import com.requisitos.hellkaiser.rm.model.BaseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.Link;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Optional;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

abstract public class BasicRestService<Model extends BaseModel, Repository extends JpaRepository, Filter>
        implements RestServiceInterface<Model, Repository, Filter> {

    @Autowired
    Repository repo;

    @Override
    public Model get(Long id) {
        Optional<Model> resource = repo.findById(id);
        resource.orElseThrow(() -> new EmptyResultDataAccessException(1));
        return resource.get();
    }

    @Override
    public Model save(Model resource, Authentication auth) {
        return (Model)repo.save(resource);
    }

    @Override
    public Model update(Model resource, Long id,String... ignoredProperties) {
        Model fromDb = get(id);

        if(ignoredProperties.length == 0) {
            BeanUtils.copyProperties(resource, fromDb, resource.getIgnored());
        }

        return  (Model)repo.save(fromDb);
    }

    @Override
    public Model delete(Long id) {
        Model model = get(id);
        repo.delete(model);
        return null;
    }

    @Override
    @Deprecated
    public Link generateSelfLink(Long id, Class<RestControllerInterface> controller){
        //HttpEntity<Model> method = methodOn(RestControllerInterface.class).findById(id);
        return linkTo(methodOn(controller).findById(id)).withSelfRel();
    }

}
