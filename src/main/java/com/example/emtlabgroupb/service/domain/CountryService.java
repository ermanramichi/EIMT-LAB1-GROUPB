package com.example.emtlabgroupb.service.domain;

import com.example.emtlabgroupb.model.domain.Country;
import com.example.emtlabgroupb.model.dto.CreateCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Country save(CreateCountryDto dto);
    Optional<Country> update(Long id, CreateCountryDto dto);
    Optional<Country> deleteById(Long id);
}
