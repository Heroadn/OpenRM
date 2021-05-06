package com.requisitos.hellkaiser.rm.generic;

import com.requisitos.hellkaiser.rm.event.RecursoCriadoEvento;
import com.requisitos.hellkaiser.rm.model.BaseModel;
import com.requisitos.hellkaiser.rm.resources.UsuarioController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

abstract public class GenericRestController<Model extends BaseModel,Filter,Repository extends JpaRepository,Service extends RestServiceInterface<Model, Repository, Filter>> implements RestControllerInterface<Model, Filter> {

    private Repository repository;

    @Autowired
    private Service service;

    @Autowired
    ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<Page<Model>> findAll(Filter filter, Pageable pageable) {
        Page<Model> list = service.filter(filter,pageable);
        list.forEach(model -> {
            model.add(linkTo(methodOn(this.getClass()).findById(model.ID)).withSelfRel());
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<Model> findById(@PathVariable long id) {
        Model model = service.get(id);
        model.add(linkTo(methodOn(this.getClass()).findById(model.ID)).withSelfRel());
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Model> save(Model model, HttpServletResponse response, Authentication auth) {
        Model fromDb = service.save(model,auth);
        publisher.publishEvent(new RecursoCriadoEvento(this, response, fromDb.getID()));
        return ResponseEntity.status(HttpStatus.CREATED).body(fromDb);
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<Model> update(@PathVariable long id, Model model) {
        Model updated = service.update(model,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(updated);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Model> delete(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.delete(id));
    }
}
