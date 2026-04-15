package com.example.emtlabgroupb.service.application;

import com.example.emtlabgroupb.model.domain.ActivityLog;
import com.example.emtlabgroupb.model.dto.DisplayAccommodationDto;
import com.example.emtlabgroupb.model.dto.CreateAccommodationDto;
import com.example.emtlabgroupb.model.dto.PopularAccommodationDto;
import com.example.emtlabgroupb.model.dto.PopularHostDto;
import com.example.emtlabgroupb.model.projection.AccommodationExtendedProjection;
import com.example.emtlabgroupb.model.projection.AccommodationShortProjection;
import com.example.emtlabgroupb.model.views.AccommodationViewEntity;
import com.example.emtlabgroupb.model.views.CategoryStatsEntity;
import com.example.emtlabgroupb.model.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccommodationApplicationService {

    Page<PopularAccommodationDto> findMostPopularAccommodations(Pageable pageable);
    Page<PopularHostDto> findMostPopularHosts(Pageable pageable);

    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDto> findWithHostById(Long id);

    Page<DisplayAccommodationDto> findAll(Pageable pageable);

    Page<DisplayAccommodationDto> findFiltered(Category category, Long hostId, String countryName,
                                               Integer numRooms, Boolean hasAvailableRooms, Pageable pageable);

    Page<AccommodationShortProjection> findAllShortProjection(Pageable pageable);

    Page<AccommodationExtendedProjection> findAllExtendedProjection(Pageable pageable);

    DisplayAccommodationDto create(CreateAccommodationDto accommodation);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation);

    Optional<DisplayAccommodationDto> deleteById(Long id);

    Optional<DisplayAccommodationDto> markAsRented(Long id);

    Page<AccommodationViewEntity> findAllFromView(Pageable pageable);

    Page<CategoryStatsEntity> findCategoryStats(Pageable pageable);

    Page<ActivityLog> findActivityLog(Pageable pageable);
}
