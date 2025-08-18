package com.pds1.backend_pds1.dtos.response;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public record HistoricoPostoResponse(
    UUID postoId,
    UUID combustivelId,
    Timestamp dataAlteracao,
    BigDecimal preco
) {
}
