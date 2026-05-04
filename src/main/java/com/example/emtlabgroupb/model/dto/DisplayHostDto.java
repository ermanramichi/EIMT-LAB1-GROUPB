package com.example.emtlabgroupb.model.dto;

import com.example.emtlabgroupb.model.domain.Host;

import java.time.LocalDateTime;

public record DisplayHostDto(
        Long id,
        String name,
        String surname,
        Long countryId,
        String countryName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static DisplayHostDto from(Host host) {
        return new DisplayHostDto(
                host.getId(),
                host.getName(),
                host.getSurname(),
                host.getCountry().getId(),
                host.getCountry().getName(),
                host.getCreatedAt(),
                host.getUpdatedAt()
        );
    }
}
