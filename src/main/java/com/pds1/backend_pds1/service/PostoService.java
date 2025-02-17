package com.pds1.backend_pds1.service;

import com.pds1.backend_pds1.dtos.PostoRecordDto;
import com.pds1.backend_pds1.model.PostoModel;
import com.pds1.backend_pds1.repository.PostoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostoService {

  private final PostoRepository postoRepository;

  public PostoService(PostoRepository postoRepository) {
    this.postoRepository = postoRepository;
  }

  public List<PostoModel> getAllPostos() {
    return postoRepository.findAll();
  }

  public PostoModel getPostoById(UUID id) {
    PostoModel posto = postoRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Posto com ID " + id + " não encontrado"));

    return posto;
  }

  @Transactional
  public PostoModel savePosto(PostoRecordDto postooRecordDto) {
    PostoModel posto = new PostoModel();
    posto.setNome(postooRecordDto.nome());

    return postoRepository.save(posto);
  }

}
