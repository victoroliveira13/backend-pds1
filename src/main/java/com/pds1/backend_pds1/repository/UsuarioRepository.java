package com.pds1.backend_pds1.repository;

import com.pds1.backend_pds1.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {
  UserDetails findByLogin(String login);
}
