package com.pds1.backend_pds1.repository;

import com.pds1.backend_pds1.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, UUID> {
}
