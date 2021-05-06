package com.requisitos.hellkaiser.rm.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="role")
public class Role extends BaseModel{

    private String nome;
    private String descricao;

    @ManyToMany(mappedBy="roles", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private Collection<Usuario> usuarios;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Collection<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /*
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "id_usuario",cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Usuario> usuarios = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_privilege",
            joinColumns = @JoinColumn(
                    name = "id_role", referencedColumnName = "id_role"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_privilege", referencedColumnName = "id_privilege"))
    private Collection<Privilege> privileges;*/
}
