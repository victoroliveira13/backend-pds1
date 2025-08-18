package com.pds1.backend_pds1.controller;

import com.pds1.backend_pds1.dtos.CombustivelPostoRecordDto;
import com.pds1.backend_pds1.dtos.response.CombustivelPostoResponseDto;
import com.pds1.backend_pds1.service.CombustivelPostoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/combustivelPosto")
public class CombustivelPostoController {

  private final CombustivelPostoService combustivelPostoService;

  public CombustivelPostoController(CombustivelPostoService combustivelPostoService) {
    this.combustivelPostoService = combustivelPostoService;
  }

  @GetMapping("/postos")
  public ResponseEntity<List<CombustivelPostoResponseDto>> getAllCombustivelPostoPorPosto() {
    return ResponseEntity.ok(combustivelPostoService.getAllCombustivelPostoPorPosto());
  }

  @GetMapping("/posto/{postoId}")
  public ResponseEntity<CombustivelPostoResponseDto> getByPostoId(@PathVariable UUID postoId) {
    return ResponseEntity.ok(combustivelPostoService.getCombustivelPostoByPostoId(postoId));
  }

  @GetMapping("/combustivel/{combustivelId}")
  public ResponseEntity<List<CombustivelPostoResponseDto>> getPostosPorCombustivel(@PathVariable UUID combustivelId) {
    List<CombustivelPostoResponseDto> dtos = combustivelPostoService.getPostosPorCombustivel(combustivelId);
    return ResponseEntity.ok(dtos);
  }

  @PostMapping
  public ResponseEntity<CombustivelPostoResponseDto> saveCombustivelPosto(@RequestBody CombustivelPostoRecordDto dto) {
    CombustivelPostoResponseDto response = combustivelPostoService.saveCombustivelPosto(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/media/{combustivelId}")
  public ResponseEntity<Double> getMediaCombustivel(@PathVariable UUID combustivelId) {
    return ResponseEntity.ok(combustivelPostoService.getMediaPrecoByCombustivel(combustivelId));
  }

}
