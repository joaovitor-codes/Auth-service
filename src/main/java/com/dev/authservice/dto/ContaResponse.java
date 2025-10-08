package com.dev.authservice.dto;

import java.util.UUID;

public record ContaResponse(
        UUID id,
        UUID idUsuario,
        String email
) {
}
