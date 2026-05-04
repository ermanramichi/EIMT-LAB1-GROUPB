package com.example.emtlabgroupb.model.dto;

import com.example.emtlabgroupb.model.domain.Accommodation;
import com.example.emtlabgroupb.model.domain.Category;
import com.example.emtlabgroupb.model.domain.Condition;
import com.example.emtlabgroupb.model.domain.Host;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAccommodationDto(

        @NotBlank(message = "Accommodation name is required.")
        String name,

        @NotNull(message = "Accommodation category is required.")
        Category category,

        @NotNull(message = "Host id is required.")
        Long hostId,

        @NotNull(message = "Accommodation condition is required.")
        Condition condition,

        @NotNull(message = "Number of rooms is required.")
        @Min(value = 1, message = "Number of rooms must be at least 1.")
        Integer numRooms
) {
        public Accommodation toAccommodation(Host host) {
                return new Accommodation(name, category, host, condition, numRooms);
        }
}