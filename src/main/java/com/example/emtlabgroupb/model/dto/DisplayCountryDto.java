package com.example.emtlabgroupb.model.dto;

import com.example.emtlabgroupb.model.domain.Country;

public record DisplayCountryDto(
        Long id,
        String name,
        String continent
) {
    public static DisplayCountryDto from(Country country) {
        return new DisplayCountryDto(
                country.getId(),
                country.getName(),
                country.getContinent()
        );
    }
}
