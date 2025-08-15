package com.pds1.backend_pds1.dtos.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CombustivelPostoResponseDto(
    UUID id,
    String razaoSocial,
    String logradouro,
    String numero,
    String cidade,
    List<Combustivel> combustiveis
) {
  public record Combustivel(
      UUID id,
      String tipo,
      BigDecimal preco
  ) {}
}
