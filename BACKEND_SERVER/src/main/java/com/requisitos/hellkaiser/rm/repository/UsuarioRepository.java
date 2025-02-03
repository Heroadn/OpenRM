package com.requisitos.hellkaiser.rm.repository;

import com.requisitos.hellkaiser.rm.model.Usuario;
import com.requisitos.hellkaiser.rm.repository.usuario.UsuarioRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable>, UsuarioRepositoryQuery {
	Boolean existsByEmail(String email);
	Boolean existsByNome(String nome);
	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findByNome(String nome);
}
