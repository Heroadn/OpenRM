package com.requisitos.hellkaiser.rm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name ="comentario")
public class Comentario extends BaseModel {

    private String texto;

    @CreationTimestamp
    private LocalDate data_comentario;

    @JoinColumn(name = "id_usuario")
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    @JsonIgnore
    @JoinColumn(name = "id_post")
    @OneToOne(cascade = CascadeType.ALL)
    private Post post;

    public Comentario(){
        super.setIgnored(
                Comentario_.I_D,
                Comentario_.USUARIO,
                Comentario_.POST
        );
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDate getData_comentario() {
        return data_comentario;
    }

    public void setData_comentario(LocalDate data_comentario) {
        this.data_comentario = data_comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


}
