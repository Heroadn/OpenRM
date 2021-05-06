package com.requisitos.hellkaiser.rm.security;

import java.util.List;

public class Token {
    public long id_usuario;
    public long exp;
    public String client_id;
    public String jti;
    public List<String> scope;
    public String user_name;

    @Override
    public String toString() {
        return "Token{" +
                "id_usuario=" + id_usuario +
                ", exp=" + exp +
                ", client_id='" + client_id + '\'' +
                ", jti='" + jti + '\'' +
                ", scope=" + scope +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}
