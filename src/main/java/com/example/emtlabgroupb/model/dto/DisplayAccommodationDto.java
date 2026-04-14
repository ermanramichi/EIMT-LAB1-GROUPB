package com.example.emtlabgroupb.model.dto;

import com.example.emtlabgroupb.model.domain.Accommodation;
import com.example.emtlabgroupb.model.domain.Category;
import com.example.emtlabgroupb.model.domain.Condition;

import java.time.LocalDateTime;

public record DisplayAccommodationDto(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String name,
        Category category,
        Long hostId,
        String hostFullName,
        Condition condition,
        Integer numRooms,
        Boolean rented
) {
    public static DisplayAccommodationDto from(Accommodation accommodation) {
        return new DisplayAccommodationDto(
                accommodation.getId(),
                accommodation.getCreatedAt(),
                accommodation.getUpdatedAt(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getHost().getName() + " " + accommodation.getHost().getSurname(),
                accommodation.getCondition(),
                accommodation.getNumRooms(),
                accommodation.getRented()
        );
    }
}
