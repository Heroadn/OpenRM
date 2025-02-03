package com.requisitos.hellkaiser.rm.config;

import com.requisitos.hellkaiser.rm.security.JwtAuthConverter;
import com.requisitos.hellkaiser.rm.security.JwtCookieFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration.jwtDecoder;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtConfig jwtConfig;
    private final JwtAuthConverter jwtAuthConverter;

    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    private static final String[] ANY_AUTH_WHITELIST = {
            // -- Swagger UI v3
            "api-docs/**",
            "/api-docs/**",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "v3/api-docs/**",
            "/swagger-ui/**",
            "swagger-ui/**"
    };

    private static final String[] GET_AUTH_WHITELIST = {
            "/test/anonymous",
            "/test/anonymous/**",
            "/test/register/",
            "/test/register/**",
            "/midia/**"
    };

    private static final String[] POST_AUTH_WHITELIST = {
            "/usuario",
            "/usuario/login-refresh",
            "/usuario/login-access",
            "/test/anonymous1",
            "/test/anonymous1/**",
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtCookieFilter(jwtConfig.jwtDecoder()), AbstractPreAuthenticatedProcessingFilter.class)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, POST_AUTH_WHITELIST).permitAll()
                        .requestMatchers(HttpMethod.GET,  GET_AUTH_WHITELIST).permitAll()
                        .requestMatchers(ANY_AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                );

        http.oauth2ResourceServer((oauth2ResourceServer) ->
                oauth2ResourceServer.jwt(
                        (jwt) ->
                        {
                            jwt.jwtAuthenticationConverter(jwtAuthConverter);
                        }
                )
        );

        http.sessionManagement(
                (sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        return http.build();
    }

}