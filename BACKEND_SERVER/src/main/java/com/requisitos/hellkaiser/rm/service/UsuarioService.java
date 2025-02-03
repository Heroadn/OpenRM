package com.requisitos.hellkaiser.rm.service;

import com.requisitos.hellkaiser.rm.generic.BasicRestService;
import com.requisitos.hellkaiser.rm.model.*;
import com.requisitos.hellkaiser.rm.repository.*;
import com.requisitos.hellkaiser.rm.repository.filter.UsuarioFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotAuthorizedException;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService extends BasicRestService<Usuario, UsuarioRepository, UsuarioFilter> {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private KeycloakService keycloakService;

    @Override
    public Page<Usuario> filter(UsuarioFilter filter, Pageable pageable){
        Page<Usuario> list = usuarioRepository.filtrar(filter,pageable);
        return list;
    }

    @Override
    public Usuario save(Usuario usuario, Principal principal) {
        //TODO: usuario ja existe, mandar exception de recurso ja existente
        if (exists(usuario.getEmail()))
            return null;

        //Configurando usuario
        usuario.setSenha(usuario.getSenha());
        usuario.setToken(generteRandomCode(7));
        Usuario usuarioBanco = usuarioRepository.save(usuario);

        //store passwords and others into auth server
        int response = keycloakService.createAccount(usuarioBanco);

        //if the user already exists in Authentication Server
        if (response == HttpStatus.CONFLICT.value()) {
            usuarioRepository.delete(usuario);
            throw new DataIntegrityViolationException("User already exists in authentication server");
        }

        //if the auth server could not create
        //delete it from the resource server
        if (response != HttpStatus.CREATED.value()) {
            System.err.println("Authentication server could not create User");
            usuarioRepository.delete(usuario);
            return null;
        }

        return usuarioBanco;
    }

    @Override
    public Usuario update(Usuario resource, Long id, String[] ignoredProperties, Principal principal) {
        Usuario usuario = this.getByToken((JwtAuthenticationToken) principal);
        Usuario oldUser = usuarioRepository.findById(id).orElseThrow(
                () -> new DataIntegrityViolationException("ID invalido") );

        //verificando se o usuario esta alterando o seu proprio usuario
        if(id != usuario.getID())
            throw new NotAuthorizedException("Usuario so tem permisão de alterar suas informações");

        //verificando se o novo email/nome do usuario já existe
        //caso o email seja o mesmo apenas continue
        if(usuarioRepository.existsByEmail(resource.getEmail())
                && !resource.getEmail().equals(oldUser.getEmail()))
            throw new DataIntegrityViolationException("Email já existe");

        if(usuarioRepository.existsByNome(resource.getNome())
                && !resource.getNome().equals(oldUser.getNome()))
            throw new DataIntegrityViolationException("Nome já existe");

        if(resource.getSenha() != null && resource.getSenha().equals(""))
            throw new DataIntegrityViolationException("Senha não pode ser nula");

        resource.setID(oldUser.getID());
        resource.setData_criacao(oldUser.getData_criacao());
        keycloakService.updateCredential(resource, usuario.getEmail());

        //salvando usuario e dando revoke no refresh Token]
        Map<String, Object> headers = ((JwtAuthenticationToken) principal)
                .getToken().getHeaders();

        String token = (headers.get("Authorization"))
                .toString().replace("Bearer ", "");

        keycloakService.revokeRefreshToken(token);
        return usuarioRepository.save(resource);
    }

    @Override
    public Usuario delete(Long id, Principal principal) {
        Usuario usuario = this.getByToken((JwtAuthenticationToken) principal);
        if(id != usuario.getID())
            throw new NotAuthorizedException("Usuario so tem permisão de deletar suas informações");

        usuario.setID(id);
        keycloakService.removeUser(usuario);
        usuarioRepository.delete(usuario);
        return null;
    }

    public Boolean exists(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario getByEmail(String email) {
        Optional<Usuario> resource = usuarioRepository.findByEmail(email);
        resource.orElseThrow(() -> new EmptyResultDataAccessException(1));
        return resource.get();
    }

    public Usuario getByToken(JwtAuthenticationToken token) {
        String userEmail = (String) token.getTokenAttributes().get("email");
        return this.getByEmail(userEmail);
    }

    public String getAccessToken(Usuario usuario) {
        //TODO: mensagem de erro ao login falhar

        //classe que gera exception ao falhar, javax.ws.rs.NotAuthorizedException: HTTP 401 Unauthorized
        //AccessTokenResponse response = keycloakService.getAccessToken(usuario);
        return keycloakService.getAccessToken(usuario).getRefreshToken();
    }

    public String getRefreshToken(String refreshToken) {
        //TODO: verificar por errors
        return keycloakService.requestAccessToken(refreshToken);
    }

    public String generteRandomCode(int size){
        return ""+ (size * Math.random());
    }
}
