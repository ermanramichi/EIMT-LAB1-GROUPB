package com.example.emtlabgroupb.model.dto;

import com.example.emtlabgroupb.model.domain.Country;
import jakarta.validation.constraints.NotBlank;

public record CreateCountryDto(
        @NotBlank(message = "Country name is required.") String name,
        @NotBlank(message = "Continent is required.") String continent
) {
    public Country toCountry() {
        return new Country(name, continent);
    }
}
