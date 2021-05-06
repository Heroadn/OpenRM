package com.requisitos.hellkaiser.rm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name ="usuario")
public class Usuario extends BaseModel {
	@Column(nullable=false)
	private String nome;
	@Column(unique=true, nullable=false)
	private String email;
	@Column(nullable=false)
	private String senha;
	private String descricao;
	@Column(nullable=false)
	private String token;
	private String oauth;
	private String facebook;
	private String linkedin;
	private int status;
	@CreationTimestamp
	private LocalDate data_criacao;

	public Usuario(){
		super.setIgnored(
				Usuario_.I_D,
				Usuario_.ROLES,
				Usuario_.DATA_CRIACAO
		);
	}

	@ManyToMany(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinTable(
			name="usuario_role",
			joinColumns={@JoinColumn(name="id_usuario",     referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="id_role", referencedColumnName="id")})
	private Collection<Role> roles;

	//@ManyToMany(mappedBy = "usuario")
	@OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
	private Collection<Equipe> equipes = new HashSet<>();

	public void addEquipe(Equipe equipe) {
		this.equipes.add(equipe);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOauth() {
		return oauth;
	}

	public void setOauth(String oauth) {
		this.oauth = oauth;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDate getData_criacao() { return data_criacao; }

	public void setData_criacao(LocalDate data_criacao) { this.data_criacao = data_criacao; }

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Collection<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(Collection<Equipe> equipes) {
		this.equipes = equipes;
	}
}
