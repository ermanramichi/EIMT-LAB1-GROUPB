package com.example.emtlabgroupb.model.dto;

public record PopularAccommodationDto(
        Long accommodationId,
        String accommodationName,
        Long bookingCount
) {}