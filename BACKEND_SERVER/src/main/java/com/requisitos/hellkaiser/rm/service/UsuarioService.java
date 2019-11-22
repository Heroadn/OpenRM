package com.requisitos.hellkaiser.rm.service;

import com.requisitos.hellkaiser.rm.generic.BasicRestService;
import com.requisitos.hellkaiser.rm.model.*;
import com.requisitos.hellkaiser.rm.repository.*;
import com.requisitos.hellkaiser.rm.repository.filter.UsuarioFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BasicRestService<Usuario, UsuarioRepository, UsuarioFilter> {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<Usuario> filter(UsuarioFilter filter, Pageable pageable){
        Page<Usuario> list = usuarioRepository.filtrar(filter,pageable);
        return list;
    }

    @Override
    public Usuario save(Usuario usuario, Authentication auth) {
        //Configurando usuario
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuario.setToken(generteRandomCode(7));
        Usuario usuarioBanco = usuarioRepository.save(usuario);

        //Adicionando permissoes a usuario
        Role role = roleRepository.findByNome("STANDARD_USER");
        role.getUsuarios().add(usuarioBanco);
        roleRepository.save(role);

        return usuarioBanco;
    }

    public String generteRandomCode(int size){
        return ""+ (size * Math.random());
    }
}
