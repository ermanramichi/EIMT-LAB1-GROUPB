package com.example.emtlabgroupb.model.dto;

/**
 * Response returned by the {@code /api/auth/login} and {@code /api/auth/register} endpoints.
 *
 * <p>{@code role} contains the UI-facing display name (e.g. {@code USER}, {@code ADMINISTRATOR})
 * rather than the underlying {@code ROLE_*} authority.</p>
 */
public record JwtResponseDto(
        String token,
        String username,
        String name,
        String role
) {}
