package com.dev.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ContaRequest(
        @Email(message = "Email inválido")
        @NotBlank(message = "O email é obrigatório")
        String email,
        @NotBlank(message = "A senha é obrigatória")
        String senha,
        @NotBlank(message = "O ID do aluno é obrigatório")
        UUID alunoId
) {
}
