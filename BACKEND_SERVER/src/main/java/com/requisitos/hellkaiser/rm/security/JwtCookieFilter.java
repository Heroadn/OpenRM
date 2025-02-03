package com.requisitos.hellkaiser.rm.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;


//searches for ${JWT_ACCESS_COOKIE_NAME} and adds it as header for every request
public class JwtCookieFilter extends OncePerRequestFilter {
    private JwtDecoder jwtDecoder;

    public JwtCookieFilter(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    //@Value("${JWT_ACCESS_COOKIE_NAME}")
    private final String COOKIE_NAME = "refreshToken";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        CustomHeaderRequestWrapper customRequest
                = new CustomHeaderRequestWrapper(request);

        // Extract the JWT from the cookie
        findCookie(request, COOKIE_NAME)
                .ifPresent(cookie ->
                {
                    addAuthorizationHeaderIfValid(customRequest, cookie);
                }
        );

        // Continue the filter chain
        filterChain.doFilter(customRequest, response);
    }

    private void addAuthorizationHeaderIfValid(CustomHeaderRequestWrapper customRequest, Cookie cookie) {
        try {
            Jwt jwt = jwtDecoder.decode(cookie.getValue());
            customRequest.addHeader(
                    "Authorization", "Bearer " + cookie.getValue());
        }catch (JwtValidationException ignored)
        {
        }
    }

    private Optional<Cookie> findCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if(cookies == null)
            return Optional.empty();

        return Arrays.stream(cookies)
                .filter( cookie -> cookie.getName().equals(name))
                .findFirst();
    }
}
