package com.pds1.backend_pds1.dtos;

public record EnderecoRecordDto (
    String logradouro,
    String bairro,
    String cidade,
    String cep
) {}
