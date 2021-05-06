package com.requisitos.hellkaiser.rm.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;


@Entity
@Table(name ="requisito")
public class Requisito extends BaseModel {

    private String codigo;

    private String nome;

    private int prioridade;

    @Column(columnDefinition = "integer default 0")
    private int concluido;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_projeto")
    private Projeto projeto;

    @JsonIgnore
    @JoinColumn(name = "id_iteracao")
    @OneToOne(cascade = CascadeType.ALL)
    private Iteracao iteracao;

    public Requisito(){
        super.setIgnored(
                Requisito_.I_D
        );
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getConcluido() {
        return concluido;
    }

    public void setConcluido(int concluido) {
        this.concluido = concluido;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Iteracao getIteracao() {
        return iteracao;
    }

    public void setIteracao(Iteracao iteracao) {
        this.iteracao = iteracao;
    }

    @Override
    public String toString() {
        return "Requisito{" +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", prioridade=" + prioridade +
                ", concluido=" + concluido +
                ", projeto=" + projeto +
                ", iteracao=" + iteracao +
                '}';
    }
}
