package com.requisitos.hellkaiser.rm.service;

import com.requisitos.hellkaiser.rm.generic.BasicRestService;
import com.requisitos.hellkaiser.rm.model.*;
import com.requisitos.hellkaiser.rm.repository.*;
import com.requisitos.hellkaiser.rm.repository.filter.EquipeFilter;
import com.requisitos.hellkaiser.rm.resources.EquipeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

@Service
public class EquipeService extends BasicRestService<Equipe,EquipeRepository, EquipeFilter> {
    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProjetoRepository projetoRepository;

    @Override
    public Page<Equipe> filter(EquipeFilter filter, Pageable pageable){
        Page<Equipe> list = equipeRepository.filtrar(filter,pageable);
        return list;
    }

    public Equipe buscarEquipePorId(long id){ return get(id); }

    public Usuario adicionarUsuario(long id_equipe, Usuario usuario){
        /*Temporario*/
        Usuario fromDb = usuarioRepository.findById(usuario.getID()).orElse(null);

        /*Adicionando usuario a equipe*/
        Equipe equipe = buscarEquipePorId(id_equipe);
        //equipe.addUsuario(fromDb);
        equipeRepository.save(equipe);
        return fromDb;
    }

}
