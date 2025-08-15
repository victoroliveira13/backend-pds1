package com.pds1.backend_pds1.controller;

import com.pds1.backend_pds1.dtos.CombustivelRecordDto;
import com.pds1.backend_pds1.model.CombustivelModel;
import com.pds1.backend_pds1.service.CombustivelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/combustivel")
public class CombustivelController {

  private final CombustivelService combustivelService;

  public CombustivelController(CombustivelService combustivelService) {
    this.combustivelService = combustivelService;
  }

  @GetMapping
  public ResponseEntity<List<CombustivelModel>> getAllCombustivel() {
    List<CombustivelModel> combustiveis = combustivelService.listAllCombustiveis();
    return ResponseEntity.ok(combustiveis);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CombustivelModel> getCombustivelById(@PathVariable UUID id) {
    CombustivelModel combustivel = combustivelService.getCombustivelById(id);
    return ResponseEntity.ok(combustivel);
  }

  @PostMapping
  public ResponseEntity<CombustivelModel> saveCombustivel(@RequestBody CombustivelRecordDto combustivelRecordDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(combustivelService.saveCombustivel(combustivelRecordDto));
  }
}
