package com.requisitos.hellkaiser.rm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="equipe")
public class Equipe extends BaseModel {
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_projeto")
    private Projeto projeto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    /*
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "membro", joinColumns = { @JoinColumn(name = "id_equipe") }, inverseJoinColumns = { @JoinColumn(name = "id_usuario") })
    Collection<Usuario> membros = new HashSet<>();*/

    public Equipe(){
        //super.setIgnored(Equipe_.I_D);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Projeto getProjeto() { return projeto; }

    public void setProjeto(Projeto projeto) { this.projeto = projeto; }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
