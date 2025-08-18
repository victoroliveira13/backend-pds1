package com.pds1.backend_pds1.controller;

import com.pds1.backend_pds1.dtos.response.HistoricoPostoResponse;
import com.pds1.backend_pds1.service.HistoricoPostoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/historico")
public class HistoricoPostoController {

  private final HistoricoPostoService historicoPostoService;

  public HistoricoPostoController(HistoricoPostoService historicoPostoService) {
    this.historicoPostoService = historicoPostoService;
  }

  @GetMapping
  public ResponseEntity<List<HistoricoPostoResponse>> listAllHistoricoPostos() {
    List<HistoricoPostoResponse> historicoPostos = historicoPostoService.getAllHistoricoPostos();
    return ResponseEntity.ok(historicoPostos);
  }

  @GetMapping("/posto/{postoId}")
  public ResponseEntity<List<HistoricoPostoResponse>> listHistoricoByPosto(@PathVariable UUID postoId) {
    List<HistoricoPostoResponse> historicoPostos = historicoPostoService.getHistoricoByPosto(postoId);
    return ResponseEntity.ok(historicoPostos);
  }

  @GetMapping("/combustivel/{combustivelId}")
  public ResponseEntity<List<HistoricoPostoResponse>> listHistoricoByCombustivel(
      @PathVariable UUID combustivelId) {
    return ResponseEntity.ok(historicoPostoService.getHistoricoByCombustivel(combustivelId));
  }

  @GetMapping("/posto/{postoId}/combustivel/{combustivelId}")
  public ResponseEntity<List<HistoricoPostoResponse>> listHistoricoByPostoAndCombustivel(
      @PathVariable UUID postoId,
      @PathVariable UUID combustivelId) {
    return ResponseEntity.ok(historicoPostoService.getHistoricoByPostoAndCombustivel(postoId, combustivelId));
  }

}
