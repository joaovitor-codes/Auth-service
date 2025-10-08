package com.dev.authservice.dto;

public record AuthenticationDto(
        String login,
        String password) {
}
