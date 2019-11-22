package com.requisitos.hellkaiser.rm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="iteracao")
public class Iteracao extends BaseModel {

    private String nome;

    @Column(columnDefinition = "integer default 0")
    private int concluido;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_projeto")
    private Projeto projeto;

    @OneToMany(mappedBy="iteracao",cascade = CascadeType.ALL, orphanRemoval = true)
    Collection<Requisito> requisitos = new HashSet<>();

    public Iteracao(){ super.setIgnored(Iteracao_.I_D, Iteracao_.PROJETO); }

    public void addRequisito(Requisito requisito){ this.requisitos.add(requisito); }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getConcluido() { return concluido; }

    public void setConcluido(int concluido) { this.concluido = concluido; }

    public Collection<Requisito> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(Collection<Requisito> requisitos) {
        this.requisitos = requisitos;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
}
