package com.requisitos.hellkaiser.rm.security;


import com.requisitos.hellkaiser.rm.model.Role;
import com.requisitos.hellkaiser.rm.model.Usuario;
import com.requisitos.hellkaiser.rm.repository.RoleRepository;
import com.requisitos.hellkaiser.rm.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /*
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = userRepository.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        return new UsuarioSistema(usuario);
    }

    private UserDetails validateUser(String email,List<GrantedAuthority> listGrantAuthority, User user) {
        UserDetails userDetails= null;
        if(user!=null){
            boolean accountNonLocked=false;
            boolean enabledUser=true;
            boolean accountNonExpired=true;
            boolean credentialsNonExpired=true;
            userDetails = new  org.springframework.security.core.userdetails.User(email, user.getPassword(), enabledUser, accountNonExpired, credentialsNonExpired, accountNonLocked, listGrantAuthority);
        }
        return userDetails;
    }*/

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = userRepository.findByEmail(email);
        Collection<? extends GrantedAuthority> roles;
        UsuarioSistema logged = null;

        if (user != null) {
            roles = getAuthorities(user.getRoles());
            logged = new UsuarioSistema(user,roles);
        }else{
            throw new UsernameNotFoundException("Usuario not found");
        }

        return logged;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(roles);
    }

    /*Recebe uma lista com as autoridades do usuario*/
    private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getNome()));
        }
        return authorities;
    }
}