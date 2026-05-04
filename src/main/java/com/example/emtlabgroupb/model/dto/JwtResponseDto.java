package com.example.emtlabgroupb.model.dto;

public record JwtResponseDto(
        String token,
        String username,
        String name,
        String role
) {}
