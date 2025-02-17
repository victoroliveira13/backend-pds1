package com.pds1.backend_pds1.controller;

import com.pds1.backend_pds1.dtos.PostoRecordDto;
import com.pds1.backend_pds1.model.PostoModel;
import com.pds1.backend_pds1.service.PostoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posto")
public class PostoController {

  private final PostoService postoService;

  public PostoController(PostoService postoService) {
    this.postoService = postoService;
  }

  @GetMapping
  public ResponseEntity<List<PostoModel>> listAllPostos() {
    List<PostoModel> postos = postoService.getAllPostos();
    return ResponseEntity.ok(postos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostoModel> getPostoById(@PathVariable UUID id) {
    PostoModel posto = postoService.getPostoById(id);
    return ResponseEntity.ok(posto);
  }

  @PostMapping
  public ResponseEntity<PostoModel> savePosto(@RequestBody PostoRecordDto postoRecordDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(postoService.savePosto(postoRecordDto));
  }
}
