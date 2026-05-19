package com.example.emtlabgroupb.service.domain.impl;

import com.example.emtlabgroupb.model.domain.Country;
import com.example.emtlabgroupb.model.domain.Host;
import com.example.emtlabgroupb.model.dto.CreateHostDto;
import com.example.emtlabgroupb.repository.CountryRepository;
import com.example.emtlabgroupb.repository.HostRepository;
import com.example.emtlabgroupb.service.domain.HostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public HostServiceImpl(HostRepository hostRepository,
                           CountryRepository countryRepository) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findWithCountryById(id);
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    @Transactional
    public Host save(CreateHostDto dto) {
        Country country = countryRepository.findById(dto.countryId())
                .orElseThrow(() -> new IllegalArgumentException("Country with id " + dto.countryId() + " not found."));
        Host host = new Host(dto.name(), dto.surname(), country);
        return hostRepository.save(host);
    }

    @Override
    @Transactional
    public Optional<Host> update(Long id, CreateHostDto dto) {
        return hostRepository.findById(id).map(existing -> {
            existing.setName(dto.name());
            existing.setSurname(dto.surname());
            if (!existing.getCountry().getId().equals(dto.countryId())) {
                Country country = countryRepository.findById(dto.countryId())
                        .orElseThrow(() -> new IllegalArgumentException("Country with id " + dto.countryId() + " not found."));
                existing.setCountry(country);
            }
            return hostRepository.save(existing);
        });
    }

    @Override
    @Transactional
    public Optional<Host> deleteById(Long id) {
        Optional<Host> existing = hostRepository.findById(id);
        existing.ifPresent(hostRepository::delete);
        return existing;
    }
}
