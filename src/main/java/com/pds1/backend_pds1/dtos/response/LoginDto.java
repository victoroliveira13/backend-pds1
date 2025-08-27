package com.pds1.backend_pds1.dtos.response;

import com.pds1.backend_pds1.roles.UserRole;

import java.util.UUID;

public record LoginDto(String token, String nome, String email, UserRole role, UUID id) {
}