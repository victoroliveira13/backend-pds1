package com.pds1.backend_pds1.controller;


import com.pds1.backend_pds1.dtos.UsuarioRecordDto;
import com.pds1.backend_pds1.model.UsuarioModel;
import com.pds1.backend_pds1.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public ResponseEntity<List<UsuarioModel>> listAllUsuarios() {
    List<UsuarioModel> usuarios = usuarioService.getAllUsuarios();
    return ResponseEntity.ok(usuarios);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsuarioModel> getUsuarioById(@PathVariable UUID id) {
    UsuarioModel usuario = usuarioService.getUsuarioById(id);
    return ResponseEntity.ok(usuario);
  }

  @PostMapping
  public ResponseEntity<UsuarioModel> savePessoa(@RequestBody UsuarioRecordDto usuarioRecordDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuarioRecordDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UsuarioModel> updateUsuario(
      @PathVariable UUID id,
      @RequestBody UsuarioRecordDto usuarioRecordDto
  ) {
    return ResponseEntity.ok(usuarioService.updateUsuario(id, usuarioRecordDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUsuario(@PathVariable UUID id) {
    usuarioService.deleteUsuario(id);
    return ResponseEntity.noContent().build();
  }

}
