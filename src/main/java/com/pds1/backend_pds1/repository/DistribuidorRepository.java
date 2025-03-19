package com.pds1.backend_pds1.repository;

import com.pds1.backend_pds1.model.DistribuidorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DistribuidorRepository extends JpaRepository<DistribuidorModel, UUID> {
}
