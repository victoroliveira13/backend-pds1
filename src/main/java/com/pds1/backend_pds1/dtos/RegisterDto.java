package com.pds1.backend_pds1.dtos;

import com.pds1.backend_pds1.roles.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterDto(
    @NotBlank(message = "Nome é obrigatório") String nome,
    @NotBlank(message = "Login é obrigatório") String login,
    @NotBlank(message = "Senha é obrigatória") String senha,
    @NotBlank(message = "Email é obrigatório")
    @Pattern(
        regexp = "^(?=.{1,64}@)[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
        message = "Formato de email inválido"
    )
    @Email(message = "Email inválido") String email,
    @NotNull(message = "Role é obrigatória") UserRole role
) {}
