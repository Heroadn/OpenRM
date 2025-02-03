package com.requisitos.hellkaiser.rm.resources;

import com.requisitos.hellkaiser.rm.generic.GenericRestController;
import com.requisitos.hellkaiser.rm.model.Usuario;
import com.requisitos.hellkaiser.rm.repository.UsuarioRepository;
import com.requisitos.hellkaiser.rm.repository.filter.UsuarioFilter;
import com.requisitos.hellkaiser.rm.service.UsuarioService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/usuario", produces = "application/hal+json")
public class UsuarioController extends GenericRestController<Usuario,UsuarioFilter,UsuarioRepository, UsuarioService> {
    /**
     * metodo que aceita as credencias de login,
     * e com isso retorna um access token
     *
     * @param  usuario dados de login
     * @return access token usado para obter refresh token
     */
    @PostMapping(value = "/login-access")
    public ResponseEntity<String> loginAccessToken(
            @RequestBody Usuario usuario,
            HttpServletResponse response) {
        String token = service.getAccessToken(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    /**
     * Token necessario para acessar apis com restrição de login
     * ele tambem contem privilegios de acesso como admin/user,
     * pode ser usar repetidamente ate o prazo do refreshToken
     *
     * @param  accessToken obtido com dados de login do usuario
     * @return              access token usado para acessar apis com login
     */
    @PostMapping(value = "/login-refresh")
    public ResponseEntity<String> loginRefreshToken(
            @RequestBody String accessToken,
            HttpServletResponse response)
    {
        String token = service.getRefreshToken(accessToken);

        // Set the token in an HTTP-only cookie
        Cookie cookie = new Cookie("refreshToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Use HTTPS
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    /**
     * Increment a value by delta and return the new value.
     *
     * @param  auth    token jwt de acesso
     * @return         access token usado para acessar apis com login
     */
    @GetMapping(value = "/meu-usuario")
    public ResponseEntity<Usuario> meuUsuario(
            Principal principal) {
        Usuario usuario = service.getByToken((JwtAuthenticationToken) principal);
        //UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        //usuarioDTO.setImages(service.getLinkImages(usuario));
        //return ResponseEntity.status(HttpStatus.OK).body(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }
}
