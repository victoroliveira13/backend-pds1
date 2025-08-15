package com.pds1.backend_pds1.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record CombustivelPostoRecordDto(
    UUID combustivel_id,
    UUID posto_id,
    BigDecimal preco
) {}