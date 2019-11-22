package com.requisitos.hellkaiser.rm.resources;

import com.requisitos.hellkaiser.rm.generic.GenericRestController;
import com.requisitos.hellkaiser.rm.model.Requisito;
import com.requisitos.hellkaiser.rm.repository.RequisitoRepository;
import com.requisitos.hellkaiser.rm.repository.filter.RequisitoFilter;
import com.requisitos.hellkaiser.rm.service.RequisitoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requisito")
@Api("Operações de gerenciamento de requisitos")
public class RequisitoController extends GenericRestController<Requisito,RequisitoFilter,RequisitoRepository,RequisitoService> {

}
