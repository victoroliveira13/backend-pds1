package com.pds1.backend_pds1.service;

import com.pds1.backend_pds1.dtos.response.HistoricoPostoResponse;
import com.pds1.backend_pds1.model.HistoricoPostoModel;
import com.pds1.backend_pds1.repository.HistoricoPostoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HistoricoPostoService {

  private final HistoricoPostoRepository historicoPostoRepository;

  public HistoricoPostoService(HistoricoPostoRepository historicoPostoRepository) {
    this.historicoPostoRepository = historicoPostoRepository;
  }

  public List<HistoricoPostoResponse> getAllHistoricoPostos() {
    return historicoPostoRepository.findAll()
        .stream()
        .map(this::toResponse)
        .toList();
  }

  public List<HistoricoPostoResponse> getHistoricoByPosto(UUID postoId) {
    return historicoPostoRepository.findByPostoId(postoId)
        .stream()
        .map(this::toResponse)
        .toList();
  }

  public List<HistoricoPostoResponse> getHistoricoByCombustivel(UUID combustivelId) {
    return historicoPostoRepository.findByCombustivelId(combustivelId)
        .stream()
        .map(this::toResponse)
        .toList();
  }

  public List<HistoricoPostoResponse> getHistoricoByPostoAndCombustivel(UUID postoId, UUID combustivelId) {
    return historicoPostoRepository.findByPostoIdAndCombustivelId(postoId, combustivelId)
        .stream()
        .map(this::toResponse)
        .toList();
  }

  private HistoricoPostoResponse toResponse(HistoricoPostoModel h) {
    return new HistoricoPostoResponse(
        h.getPosto().getId(),
        h.getCombustivel().getId(),
        h.getDataAlteracao(),
        h.getPreco()
    );
  }
}
