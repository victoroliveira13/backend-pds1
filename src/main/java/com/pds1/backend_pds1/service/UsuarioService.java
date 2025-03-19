package com.pds1.backend_pds1.service;

import com.pds1.backend_pds1.dtos.UsuarioRecordDto;
import com.pds1.backend_pds1.model.UsuarioModel;
import com.pds1.backend_pds1.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;

  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public List<UsuarioModel> getAllUsuarios() {
    return usuarioRepository.findAll();
  }

  public UsuarioModel getUsuarioById(UUID id) {
    UsuarioModel usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + id + " não encontrado"));

    return usuario;
  }

  @Transactional
  public UsuarioModel saveUsuario(UsuarioRecordDto usuarioRecordDto) {
    UsuarioModel usuario = new UsuarioModel();
    usuario.setNome(usuarioRecordDto.nome());

    return usuarioRepository.save(usuario);
  }

  @Transactional
  public UsuarioModel updateUsuario(UUID id, UsuarioRecordDto usuarioRecordDto) {
    UsuarioModel usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + id + " não encontrado"));

    if (usuarioRecordDto.nome() != null) {
      usuario.setNome(usuarioRecordDto.nome());
    }

    return usuarioRepository.save(usuario);
  }

  @Transactional
  public void deleteUsuario(UUID id) {
    UsuarioModel usuarioExistente = usuarioRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + id + " não encontrado"));

    usuarioRepository.delete(usuarioExistente);
  }

}
