package com.pds1.backend_pds1.repository;

import com.pds1.backend_pds1.model.HistoricoPostoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HistoricoPostoRepository extends JpaRepository<HistoricoPostoModel, UUID> {
  List<HistoricoPostoModel> findByPostoId(UUID postoId);
  List<HistoricoPostoModel> findByCombustivelId(UUID combustivelId);
  List<HistoricoPostoModel> findByPostoIdAndCombustivelId(UUID postoId, UUID combustivelId);
}
