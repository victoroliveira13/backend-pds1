package com.pds1.backend_pds1.service;

import com.pds1.backend_pds1.dtos.CombustivelPostoRecordDto;
import com.pds1.backend_pds1.dtos.UpdatePrecoCombustivelDto;
import com.pds1.backend_pds1.dtos.response.CombustivelPostoResponseDto;
import com.pds1.backend_pds1.model.CombustivelModel;
import com.pds1.backend_pds1.model.CombustivelPostoModel;
import com.pds1.backend_pds1.model.PostoModel;
import com.pds1.backend_pds1.repository.CombustivelPostoRepository;
import com.pds1.backend_pds1.repository.CombustivelRepository;
import com.pds1.backend_pds1.repository.PostoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CombustivelPostoService {

  private final CombustivelPostoRepository combustivelPostoRepository;
  private final CombustivelRepository combustivelRepository;
  private final PostoRepository postoRepository;

  public CombustivelPostoService(CombustivelPostoRepository combustivelPostoRepository,
                                 CombustivelRepository combustivelRepository,
                                 PostoRepository postoRepository) {
    this.combustivelPostoRepository = combustivelPostoRepository;
    this.combustivelRepository = combustivelRepository;
    this.postoRepository = postoRepository;
  }

  public CombustivelPostoResponseDto saveCombustivelPosto(CombustivelPostoRecordDto dto) {
    CombustivelModel combustivel = combustivelRepository.findById(dto.combustivel_id())
        .orElseThrow(() -> new RuntimeException("Combustível não encontrado"));

    PostoModel posto = postoRepository.findById(dto.posto_id())
        .orElseThrow(() -> new RuntimeException("Posto não encontrado"));

    CombustivelPostoModel entity = new CombustivelPostoModel();
    entity.setCombustivel(combustivel);
    entity.setPosto(posto);
    entity.setPreco(dto.preco());

    combustivelPostoRepository.save(entity);

    // Retorna o posto atualizado com todos os combustíveis
    return getCombustivelPostoByPostoId(posto.getId());
  }

  public List<CombustivelPostoResponseDto> getAllCombustivelPostoPorPosto() {
    List<CombustivelPostoModel> lista = combustivelPostoRepository.findAll();

    Map<UUID, List<CombustivelPostoModel>> agrupados = lista.stream()
        .collect(Collectors.groupingBy(cp -> cp.getPosto().getId()));

    return agrupados.entrySet().stream().map(entry -> {
      PostoModel posto = entry.getValue().get(0).getPosto();
      List<CombustivelPostoResponseDto.Combustivel> combustiveis = entry.getValue().stream()
          .map(cp -> new CombustivelPostoResponseDto.Combustivel(
              cp.getCombustivel().getId(),
              cp.getCombustivel().getTipo(),
              cp.getPreco()
          ))
          .toList();

      return new CombustivelPostoResponseDto(
          posto.getId(),
          posto.getRazaoSocial(),
          posto.getEndereco().getLogradouro(),
          posto.getNumeroEndereco(),
          posto.getEndereco().getCidade(),
          combustiveis
      );
    }).toList();
  }

  public CombustivelPostoResponseDto getCombustivelPostoByPostoId(UUID postoId) {
    List<CombustivelPostoModel> lista = combustivelPostoRepository.findByPostoId(postoId);

    if (lista.isEmpty()) {
      throw new RuntimeException("Nenhum combustível encontrado para este posto");
    }

    PostoModel posto = lista.get(0).getPosto();
    List<CombustivelPostoResponseDto.Combustivel> combustiveis = lista.stream()
        .map(cp -> new CombustivelPostoResponseDto.Combustivel(
            cp.getCombustivel().getId(),
            cp.getCombustivel().getTipo(),
            cp.getPreco()
        ))
        .toList();

    return new CombustivelPostoResponseDto(
        posto.getId(),
        posto.getRazaoSocial(),
        posto.getEndereco().getLogradouro(),
        posto.getNumeroEndereco(),
        posto.getEndereco().getCidade(),
        combustiveis
    );
  }

  public List<CombustivelPostoResponseDto> getPostosPorCombustivel(UUID combustivelId) {
    // Busca todos os registros do combustível específico
    List<CombustivelPostoModel> lista = combustivelPostoRepository.findByCombustivelId(combustivelId);

    // Agrupa por posto
    Map<UUID, List<CombustivelPostoModel>> agrupados = lista.stream()
        .collect(Collectors.groupingBy(cp -> cp.getPosto().getId()));

    // Constrói DTO por posto
    return agrupados.entrySet().stream().map(entry -> {
      PostoModel posto = entry.getValue().get(0).getPosto();
      List<CombustivelPostoResponseDto.Combustivel> combustiveis = entry.getValue().stream()
          .map(cp -> new CombustivelPostoResponseDto.Combustivel(
              cp.getCombustivel().getId(),
              cp.getCombustivel().getTipo(),
              cp.getPreco()
          ))
          .toList();

      return new CombustivelPostoResponseDto(
          posto.getId(),
          posto.getRazaoSocial(),
          posto.getEndereco().getLogradouro(),
          posto.getNumeroEndereco(),
          posto.getEndereco().getCidade(),
          combustiveis
      );
    }).toList();
  }

  public Double getMediaPrecoByCombustivel(UUID combustivelId) {
    List<CombustivelPostoModel> lista = combustivelPostoRepository.findByCombustivelId(combustivelId);

    return lista.stream()
        .mapToDouble(cp -> cp.getPreco().doubleValue())
        .average()
        .orElse(0.0);
  }

  @Transactional
  public CombustivelPostoResponseDto updatePrecoCombustivel(UpdatePrecoCombustivelDto dto) {
    // Busca o registro específico
    var registro = combustivelPostoRepository
        .findByPostoIdAndCombustivelId(dto.postoId(), dto.combustivelId())
        .orElseThrow(() -> new RuntimeException("Combustível não encontrado neste posto"));

    // Atualiza o preço
    registro.setPreco(dto.novoPreco());
    combustivelPostoRepository.save(registro);

    // Recupera todos os combustíveis do posto para montar o DTO completo
    List<CombustivelPostoResponseDto.Combustivel> combustiveis = combustivelPostoRepository
        .findByPostoId(dto.postoId())
        .stream()
        .map(c -> new CombustivelPostoResponseDto.Combustivel(
            c.getCombustivel().getId(),
            c.getCombustivel().getTipo(),
            c.getPreco()
        ))
        .toList();

    var posto = registro.getPosto();

    return new CombustivelPostoResponseDto(
        posto.getId(),
        posto.getRazaoSocial(),
        posto.getEndereco().getLogradouro(),
        posto.getNumeroEndereco(),
        posto.getEndereco().getCidade(),
        combustiveis
    );
  }

}
