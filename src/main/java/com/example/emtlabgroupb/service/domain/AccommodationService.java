package com.example.emtlabgroupb.service.domain;

import com.example.emtlabgroupb.model.domain.Accommodation;
import com.example.emtlabgroupb.model.domain.Category;
import com.example.emtlabgroupb.model.projection.AccommodationExtendedProjection;
import com.example.emtlabgroupb.model.projection.AccommodationShortProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccommodationService {
    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> findWithHostById(Long id);

    Page<Accommodation> findAll(Pageable pageable);

    Page<Accommodation> findFiltered(Category category, Long hostId, String countryName,
                                     Integer numRooms, Boolean hasAvailableRooms, Pageable pageable);

    Page<AccommodationShortProjection> findAllShortProjection(Pageable pageable);

    Page<AccommodationExtendedProjection> findAllExtendedProjection(Pageable pageable);

    Accommodation create(Accommodation accommodation);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    Optional<Accommodation> deleteById(Long id);

    Optional<Accommodation> markAsRented(Long id);
}
