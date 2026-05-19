package com.example.emtlabgroupb.model.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Payload for user registration.
 *
 * <p>{@code role} is optional. Accepted values: {@code USER}, {@code ADMINISTRATOR}
 * (or the underlying {@code ROLE_USER}, {@code ROLE_ADMIN}). If omitted or unknown,
 * the user is created with role {@code USER}.</p>
 */
public record RegisterRequestDto(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String name,
        String role
) {}
