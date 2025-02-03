package com.requisitos.hellkaiser.rm.generic;

import com.requisitos.hellkaiser.rm.model.BaseModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


public interface RestControllerInterface<Model extends BaseModel,Filter> {

    ResponseEntity<Model> findById(@PathVariable long id);

    ResponseEntity<Model> save(@RequestBody Model model, HttpServletResponse response, Authentication auth);

    ResponseEntity<Model> update(@PathVariable final long id, @RequestBody Model model, Authentication auth);

    ResponseEntity<Model> delete(@PathVariable final long id, Authentication auth);
}
