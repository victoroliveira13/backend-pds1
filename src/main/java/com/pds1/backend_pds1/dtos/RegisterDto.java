package com.pds1.backend_pds1.dtos;

import com.pds1.backend_pds1.roles.UserRole;

public record RegisterDto(String nome, String login, String senha, String email, UserRole role) {
}
