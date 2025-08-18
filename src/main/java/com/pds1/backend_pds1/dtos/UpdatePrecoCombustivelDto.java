package com.pds1.backend_pds1.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdatePrecoCombustivelDto(
    UUID postoId,
    UUID combustivelId,
    BigDecimal novoPreco
) {}
