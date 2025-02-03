package com.requisitos.hellkaiser.rm.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="projeto")
public class Projeto extends BaseModel{

    private String nome;

    private String objetivo;

    @Column(columnDefinition = "integer default 1")
    private int visivel;

    @Column(columnDefinition = "integer default 0")
    private int concluido;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy','HH:mm:ss", timezone="America/Sao_Paulo")
    @CreationTimestamp
    private Timestamp data_criacao;

    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss", timezone="America/Sao_Paulo")
    public Timestamp data_conclusao;

    @JsonIgnore
    @OneToMany(mappedBy = "projeto",cascade = CascadeType.ALL)
    private Collection<Equipe> equipes;

    @OneToMany(mappedBy = "projeto",cascade = CascadeType.ALL)
    Collection<Requisito> backlog;

    @OneToMany(mappedBy = "projeto",cascade = CascadeType.ALL)
    Collection<Iteracao>  iteracoes;

    @OneToMany(mappedBy = "projeto",cascade = CascadeType.ALL)
    Collection<Post> posts;

    public Projeto(){
        //super.setIgnored(Projeto_.I_D, Projeto_.DATA_CRIACAO);
    }

    public void addEquipe(Equipe equipe){
        this.equipes.add(equipe);
    }

    public void addRequisito(Requisito requisito){
        this.backlog.add(requisito);
    }

    public void addIteracao(Iteracao iteracao){
        this.iteracoes.add(iteracao);
    }

    public void addPost(Post post){
        this.posts.add(post);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObjetivo() { return objetivo; }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public int getVisivel() {
        return visivel;
    }

    public void setVisivel(int visivel)  {
        this.visivel = visivel;
    }

    public int getConcluido() { return concluido; }

    public void setConcluido(int concluido) { this.concluido = concluido; }

    public Collection<Requisito> getBacklog() { return backlog; }

    public void setBacklog(Collection<Requisito> backlog) { this.backlog = backlog; }

    public Collection<Equipe> getEquipes() { return equipes; }

    public void setEquipes(Collection<Equipe> equipes) { this.equipes = equipes; }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    public Collection<Iteracao> getIteracoes() {
        return iteracoes;
    }

    public void setIteracoes(Collection<Iteracao> iteracoes) {
        this.iteracoes = iteracoes;
    }

    public Timestamp getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Timestamp data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Timestamp getData_conclusao() {
        return data_conclusao;
    }

    public void setData_conclusao(Timestamp data_conclusao) {
        this.data_conclusao = data_conclusao;
    }


}
