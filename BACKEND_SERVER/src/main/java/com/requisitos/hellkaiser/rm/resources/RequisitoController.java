package com.requisitos.hellkaiser.rm.resources;

import com.requisitos.hellkaiser.rm.generic.GenericRestController;
import com.requisitos.hellkaiser.rm.model.Requisito;
import com.requisitos.hellkaiser.rm.repository.RequisitoRepository;
import com.requisitos.hellkaiser.rm.repository.filter.RequisitoFilter;
import com.requisitos.hellkaiser.rm.service.RequisitoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requisito")
//@Api("Operações de gerenciamento de requisitos")
public class RequisitoController extends GenericRestController<Requisito,RequisitoFilter,RequisitoRepository,RequisitoService> {

}
