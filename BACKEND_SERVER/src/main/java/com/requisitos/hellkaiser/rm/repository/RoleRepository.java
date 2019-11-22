package com.requisitos.hellkaiser.rm.repository;

import com.requisitos.hellkaiser.rm.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface RoleRepository extends JpaRepository<Role, Serializable> {
    Role findByNome(String nome);
}
