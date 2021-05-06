package com.requisitos.hellkaiser.rm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name ="post")
public class Post extends BaseModel{

    private String titulo;

    private String texto;

    @CreationTimestamp
    private LocalDate data_post;

    @JoinColumn(name = "id_usuario")
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    @JsonIgnore
    @JoinColumn(name = "id_projeto")
    @OneToOne(cascade = CascadeType.ALL)
    private Projeto projeto;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    Collection<Comentario> comentarios;

    public Post(){
        super.setIgnored(
                Post_.I_D,
                Post_.USUARIO,
                Post_.PROJETO,
                Post_.DATA_POST
        );
    }

    public void addComentario(Comentario comentario){
        this.comentarios.add(comentario);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDate getData_post() {
        return data_post;
    }

    public void setData_post(LocalDate data_post) {
        this.data_post = data_post;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Collection<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Collection<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}


