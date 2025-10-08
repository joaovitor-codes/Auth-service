package com.dev.authservice.dto;

import jakarta.validation.constraints.Email;

import java.util.Optional;

public record ContaUpdate(
        @Email(message = "Email inválido")
        Optional<String> email,
        Optional<String> senha
) {
}
