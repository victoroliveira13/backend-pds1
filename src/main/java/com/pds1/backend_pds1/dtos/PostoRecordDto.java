package com.pds1.backend_pds1.dtos;

import java.util.UUID;

public record PostoRecordDto(
    String razaoSocial,
    String numeroEndereco,
    String complementoEndereco,
    UUID distribuidor_id,
    UUID endereco_id
) {}
