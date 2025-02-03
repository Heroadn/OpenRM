package com.requisitos.hellkaiser.rm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtException;

@ConfigurationProperties(prefix = "spring.security.oauth2.resourceserver.jwt")
@Configuration
public class JwtConfig{
    private String issuerUri;
    private String jwkSetUri;

    @Bean
    public JwtDecoder jwtDecoder() throws JwtException {
        String jwksUri = getIssuerUri();
        return JwtDecoders.fromIssuerLocation(jwksUri);
    }

    public String getIssuerUri() {
        return issuerUri;
    }

    public void setIssuerUri(String issuerUri) {
        this.issuerUri = issuerUri;
    }

    public String getJwkSetUri() {
        return jwkSetUri;
    }

    public void setJwkSetUri(String jwkSetUri) {
        this.jwkSetUri = jwkSetUri;
    }
}
