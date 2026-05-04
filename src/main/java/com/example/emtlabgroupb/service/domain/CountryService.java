package com.example.emtlabgroupb.service.domain;

import com.example.emtlabgroupb.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
}
