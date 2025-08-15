package com.pds1.backend_pds1.service;

import com.pds1.backend_pds1.dtos.CombustivelRecordDto;
import com.pds1.backend_pds1.dtos.PostoRecordDto;
import com.pds1.backend_pds1.model.CombustivelModel;
import com.pds1.backend_pds1.model.PostoModel;
import com.pds1.backend_pds1.repository.CombustivelRepository;

import com.pds1.backend_pds1.repository.DistribuidorRepository;
import com.pds1.backend_pds1.repository.PostoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CombustivelService {

  private final CombustivelRepository combustivelRepository;

  private final DistribuidorRepository distribuidorRepository;

  public CombustivelService(CombustivelRepository combustivelRepository, DistribuidorRepository distribuidorRepository) {
    this.combustivelRepository = combustivelRepository;
    this.distribuidorRepository = distribuidorRepository;
  }

  public List<CombustivelModel> listAllCombustiveis() {
    return combustivelRepository.findAll();
  }

  public CombustivelModel getCombustivelById(UUID id) {
    CombustivelModel combustivel = combustivelRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Combustível com ID " + id + " não encontrado"));

    return combustivel;
  }

  @Transactional
  public CombustivelModel saveCombustivel(CombustivelRecordDto combustivelRecordDto) {
    var distribuidor = distribuidorRepository.findById(combustivelRecordDto.distribuidor_id())
        .orElseThrow(() -> new RuntimeException("Distribuidor não encontrado"));

    CombustivelModel combustivel = new CombustivelModel();
    combustivel.setTipo(combustivelRecordDto.tipo());
    combustivel.setDistribuidor(distribuidor);

    return combustivelRepository.save(combustivel);
  }


}
