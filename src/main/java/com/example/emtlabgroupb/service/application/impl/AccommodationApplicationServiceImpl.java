package com.example.emtlabgroupb.service.application.impl;

import com.example.emtlabgroupb.model.domain.ActivityLog;
import com.example.emtlabgroupb.model.domain.Category;
import com.example.emtlabgroupb.model.domain.Host;
import com.example.emtlabgroupb.model.dto.CreateAccommodationDto;
import com.example.emtlabgroupb.model.dto.DisplayAccommodationDto;
import com.example.emtlabgroupb.model.dto.PopularAccommodationDto;
import com.example.emtlabgroupb.model.dto.PopularHostDto;
import com.example.emtlabgroupb.model.exception.HostNotFoundException;
import com.example.emtlabgroupb.model.projection.AccommodationExtendedProjection;
import com.example.emtlabgroupb.model.projection.AccommodationShortProjection;
import com.example.emtlabgroupb.model.views.AccommodationViewEntity;
import com.example.emtlabgroupb.model.views.CategoryStatsEntity;
import com.example.emtlabgroupb.repository.AccommodationViewRepository;
import com.example.emtlabgroupb.repository.ActivityLogRepository;
import com.example.emtlabgroupb.repository.CategoryStatsRepository;
import com.example.emtlabgroupb.service.application.AccommodationApplicationService;
import com.example.emtlabgroupb.service.domain.AccommodationService;
import com.example.emtlabgroupb.service.domain.HostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostService hostService;
    private final AccommodationViewRepository accommodationViewRepository;
    private final CategoryStatsRepository categoryStatsRepository;
    private final ActivityLogRepository activityLogRepository;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService,
                                               HostService hostService,
                                               AccommodationViewRepository accommodationViewRepository,
                                               CategoryStatsRepository categoryStatsRepository,
                                               ActivityLogRepository activityLogRepository) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
        this.accommodationViewRepository = accommodationViewRepository;
        this.categoryStatsRepository = categoryStatsRepository;
        this.activityLogRepository = activityLogRepository;
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> findWithHostById(Long id) {
        return accommodationService.findWithHostById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Page<DisplayAccommodationDto> findAll(Pageable pageable) {
        return accommodationService.findAll(pageable).map(DisplayAccommodationDto::from);
    }

    @Override
    public Page<DisplayAccommodationDto> findFiltered(Category category, Long hostId, String countryName,
                                                      Integer numRooms, Boolean hasAvailableRooms, Pageable pageable) {
        return accommodationService.findFiltered(category, hostId, countryName, numRooms, hasAvailableRooms, pageable)
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public Page<AccommodationShortProjection> findAllShortProjection(Pageable pageable) {
        return accommodationService.findAllShortProjection(pageable);
    }

    @Override
    public Page<AccommodationExtendedProjection> findAllExtendedProjection(Pageable pageable) {
        return accommodationService.findAllExtendedProjection(pageable);
    }

    @Override
    public DisplayAccommodationDto create(CreateAccommodationDto dto) {
        Host host = hostService.findById(dto.hostId())
                .orElseThrow(() -> new HostNotFoundException(dto.hostId()));
        return DisplayAccommodationDto.from(accommodationService.create(dto.toAccommodation(host)));
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto dto) {
        Host host = hostService.findById(dto.hostId())
                .orElseThrow(() -> new HostNotFoundException(dto.hostId()));
        return accommodationService.update(id, dto.toAccommodation(host)).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> deleteById(Long id) {
        return accommodationService.deleteById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> markAsRented(Long id) {
        return accommodationService.markAsRented(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Page<AccommodationViewEntity> findAllFromView(Pageable pageable) {
        return accommodationViewRepository.findAll(pageable);
    }

    @Override
    public Page<CategoryStatsEntity> findCategoryStats(Pageable pageable) {
        return categoryStatsRepository.findAll(pageable);
    }

    @Override
    public Page<ActivityLog> findActivityLog(Pageable pageable) {
        return activityLogRepository.findAll(pageable);
    }

    @Override
    public Page<PopularAccommodationDto> findMostPopularAccommodations(Pageable pageable) {
        return activityLogRepository.findMostPopularAccommodations(pageable)
                .map(row -> new PopularAccommodationDto(
                        (Long) row[0],
                        (String) row[1],
                        (Long) row[2]
                ));
    }

    @Override
    public Page<PopularHostDto> findMostPopularHosts(Pageable pageable) {
        return activityLogRepository.findMostPopularHosts(pageable)
                .map(row -> new PopularHostDto(
                        (Long) row[0],
                        (Long) row[1]
                ));
    }
}
