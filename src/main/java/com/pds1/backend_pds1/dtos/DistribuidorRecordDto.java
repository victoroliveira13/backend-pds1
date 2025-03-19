package com.pds1.backend_pds1.dtos;

import java.util.List;
import java.util.UUID;

public record DistribuidorRecordDto (String nome, List<UUID> combustivel_id) {
}
