package com.example.emtlabgroupb.service.domain.impl;

import com.example.emtlabgroupb.model.domain.Country;
import com.example.emtlabgroupb.model.dto.CreateCountryDto;
import com.example.emtlabgroupb.repository.CountryRepository;
import com.example.emtlabgroupb.service.domain.CountryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    @Transactional
    public Country save(CreateCountryDto dto) {
        return countryRepository.save(dto.toCountry());
    }

    @Override
    @Transactional
    public Optional<Country> update(Long id, CreateCountryDto dto) {
        return countryRepository.findById(id).map(existing -> {
            existing.setName(dto.name());
            existing.setContinent(dto.continent());
            return countryRepository.save(existing);
        });
    }

    @Override
    @Transactional
    public Optional<Country> deleteById(Long id) {
        Optional<Country> existing = countryRepository.findById(id);
        existing.ifPresent(countryRepository::delete);
        return existing;
    }
}
