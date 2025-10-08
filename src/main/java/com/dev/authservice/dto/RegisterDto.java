package com.dev.authservice.dto;

import com.dev.authservice.model.ContasRole;

public record RegisterDto(
        String login,
        String password,
        ContasRole role
) {
}
