package com.pds1.backend_pds1.repository;

import com.pds1.backend_pds1.model.CombustivelPostoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CombustivelPostoRepository extends JpaRepository<CombustivelPostoModel, UUID> {
  List<CombustivelPostoModel> findByCombustivelId(UUID combustivelId);
  List<CombustivelPostoModel> findByPostoId(UUID postoId);
}