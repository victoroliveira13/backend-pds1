package com.pds1.backend_pds1.service;

import com.pds1.backend_pds1.dtos.CombustivelRecordDto;
import com.pds1.backend_pds1.dtos.DistribuidorRecordDto;
import com.pds1.backend_pds1.model.CombustivelModel;
import com.pds1.backend_pds1.model.DistribuidorModel;
import com.pds1.backend_pds1.repository.CombustivelRepository;
import com.pds1.backend_pds1.repository.DistribuidorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DistribuidorService {

  private final DistribuidorRepository distribuidorRepository;

  public DistribuidorService(DistribuidorRepository distribuidorRepository) {
    this.distribuidorRepository = distribuidorRepository;
  }

  public List<DistribuidorModel> listAllDistribuidores() {
    return distribuidorRepository.findAll();
  }

  public DistribuidorModel getDistribuidorById(UUID id) {
    DistribuidorModel distribuidor = distribuidorRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Distribuidor com ID " + id + " não encontrado"));

    return distribuidor;
  }

  @Transactional
  public DistribuidorModel saveDistribuidor(DistribuidorRecordDto distribuidorRecordDto) {
    DistribuidorModel distribuidor = new DistribuidorModel();
    distribuidor.setNome(distribuidorRecordDto.nome());

    return distribuidorRepository.save(distribuidor);
  }
}
