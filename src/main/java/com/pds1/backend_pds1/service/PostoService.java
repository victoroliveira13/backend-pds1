package com.pds1.backend_pds1.service;

import com.pds1.backend_pds1.dtos.PostoRecordDto;
import com.pds1.backend_pds1.model.DistribuidorModel;
import com.pds1.backend_pds1.model.EnderecoModel;
import com.pds1.backend_pds1.model.PostoModel;
import com.pds1.backend_pds1.repository.DistribuidorRepository;
import com.pds1.backend_pds1.repository.EnderecoRepository;
import com.pds1.backend_pds1.repository.PostoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostoService {

  private final PostoRepository postoRepository;
  private final DistribuidorRepository distribuidorRepository;
  private final EnderecoRepository enderecoRepository;

  public PostoService(PostoRepository postoRepository, DistribuidorRepository distribuidorRepository, EnderecoRepository enderecoRepository) {
    this.postoRepository = postoRepository;
    this.distribuidorRepository = distribuidorRepository;
    this.enderecoRepository = enderecoRepository;
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
  public PostoModel savePosto(PostoRecordDto postoRecordDto) {
    PostoModel posto = new PostoModel();
    posto.setRazaoSocial(postoRecordDto.razaoSocial());
    posto.setNumeroEndereco(postoRecordDto.numeroEndereco());
    posto.setComplementoEndereco(postoRecordDto.complementoEndereco());

    DistribuidorModel distribuidor = distribuidorRepository.findById(postoRecordDto.distribuidor_id())
        .orElseThrow(() -> new IllegalArgumentException("Distribuidor com ID " + postoRecordDto.distribuidor_id() + " não encontrado."));
    posto.setDistribuidor(distribuidor);

    EnderecoModel endereco = enderecoRepository.findById(postoRecordDto.endereco_id())
        .orElseThrow(() -> new IllegalArgumentException("Endereço com ID " + postoRecordDto.endereco_id() + " não encontrado."));
    posto.setEndereco(endereco);

    return postoRepository.save(posto);
  }

}
