package com.example.emtlabgroupb.service.application.impl;

import com.example.emtlabgroupb.model.domain.Host;
import com.example.emtlabgroupb.model.dto.CreateAccommodationDto;
import com.example.emtlabgroupb.model.dto.DisplayAccommodationDto;
import com.example.emtlabgroupb.model.exception.HostNotFoundException;
import com.example.emtlabgroupb.service.application.AccommodationApplicationService;
import com.example.emtlabgroupb.service.domain.AccommodationService;
import com.example.emtlabgroupb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationService.findAll());
    }

    @Override
    public DisplayAccommodationDto create(CreateAccommodationDto accommodation) {
        Host host = hostService.findById(accommodation.hostId()).orElseThrow(() -> new HostNotFoundException(accommodation.hostId()));

        return DisplayAccommodationDto.from(accommodationService.create(accommodation.toAccommodation(host)));
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation) {
        Host host = hostService.findById(accommodation.hostId()).orElseThrow(() -> new HostNotFoundException(accommodation.hostId()));

        return accommodationService.update(id, accommodation.toAccommodation(host)).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> deleteById(Long id) {
        return accommodationService.deleteById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> markAsRented(Long id) {
        return accommodationService.markAsRented(id).map(DisplayAccommodationDto::from);
    }

}
