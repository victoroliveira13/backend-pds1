package com.pds1.backend_pds1.controller;

import com.pds1.backend_pds1.dtos.DistribuidorRecordDto;
import com.pds1.backend_pds1.dtos.PostoRecordDto;
import com.pds1.backend_pds1.model.DistribuidorModel;
import com.pds1.backend_pds1.model.PostoModel;
import com.pds1.backend_pds1.service.DistribuidorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/distribuidor")
public class DistribuidorController {

  public final DistribuidorService distribuidorService;

  public DistribuidorController(DistribuidorService distribuidorService) {
    this.distribuidorService = distribuidorService;
  }

  @GetMapping
  public ResponseEntity<List<DistribuidorModel>> listAllDistribuidores() {
    List<DistribuidorModel> distribuidores = distribuidorService.listAllDistribuidores();
    return ResponseEntity.ok(distribuidores);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DistribuidorModel> getDistribuidorById(@PathVariable UUID id) {
    DistribuidorModel distribuidor = distribuidorService.getDistribuidorById(id);
    return ResponseEntity.ok(distribuidor);
  }

  @PostMapping
  public ResponseEntity<DistribuidorModel> saveDistribuidor(@RequestBody DistribuidorRecordDto distribuidorRecordDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(distribuidorService.saveDistribuidor(distribuidorRecordDto));
  }
}
