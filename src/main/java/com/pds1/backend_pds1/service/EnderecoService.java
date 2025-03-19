package com.pds1.backend_pds1.service;

import com.pds1.backend_pds1.dtos.EnderecoRecordDto;
import com.pds1.backend_pds1.dtos.PostoRecordDto;
import com.pds1.backend_pds1.model.EnderecoModel;
import com.pds1.backend_pds1.model.PostoModel;
import com.pds1.backend_pds1.repository.EnderecoRepository;
import com.pds1.backend_pds1.repository.PostoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnderecoService {

  private final EnderecoRepository enderecoRepository;

  public EnderecoService(EnderecoRepository enderecoRepository) {
    this.enderecoRepository = enderecoRepository;
  }

  public List<EnderecoModel> getAllEnderecos() {
    return enderecoRepository.findAll();
  }

  public EnderecoModel getEnderecoById(UUID id) {
    EnderecoModel endereco = enderecoRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Endereço com ID " + id + " não encontrado"));

    return endereco;
  }

  @Transactional
  public EnderecoModel saveEndereco(EnderecoRecordDto enderecoRecordDto) {
    EnderecoModel endereco = new EnderecoModel();
    endereco.setLogradouro(enderecoRecordDto.logradouro());
    endereco.setCep(enderecoRecordDto.cep());
    endereco.setBairro(enderecoRecordDto.bairro());
    endereco.setCidade(enderecoRecordDto.cidade());

    return enderecoRepository.save(endereco);
  }
}
