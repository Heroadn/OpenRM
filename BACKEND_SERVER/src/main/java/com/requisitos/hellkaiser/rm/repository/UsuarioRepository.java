package com.requisitos.hellkaiser.rm.repository;

import com.requisitos.hellkaiser.rm.model.Usuario;
import com.requisitos.hellkaiser.rm.repository.usuario.UsuarioRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface UsuarioRepository extends JpaRepository<Usuario, Serializable>, UsuarioRepositoryQuery {
	Usuario findByEmail(String email);
	Usuario findByNome(String nome);
}
