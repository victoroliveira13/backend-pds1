package com.pds1.backend_pds1.controller;

import com.pds1.backend_pds1.dtos.EnderecoRecordDto;
import com.pds1.backend_pds1.model.EnderecoModel;
import com.pds1.backend_pds1.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

  private final EnderecoService enderecoService;

  public EnderecoController(EnderecoService enderecoService) {
    this.enderecoService = enderecoService;
  }

  @GetMapping
  public ResponseEntity<List<EnderecoModel>> listAllEnderecos() {
    List<EnderecoModel> enderecos = enderecoService.getAllEnderecos();
    return ResponseEntity.ok(enderecos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EnderecoModel> getEnderecoById(@PathVariable UUID id) {
    EnderecoModel endereco = enderecoService.getEnderecoById(id);
    return ResponseEntity.ok(endereco);
  }

  @PostMapping
  public ResponseEntity<EnderecoModel> saveEndereco(@RequestBody EnderecoRecordDto enderecoRecordDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.saveEndereco(enderecoRecordDto));
  }


}
