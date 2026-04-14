package com.example.emtlabgroupb.service.domain.impl;

import com.example.emtlabgroupb.events.AccommodationFullyOccupiedEvent;
import com.example.emtlabgroupb.events.AccommodationRentedEvent;
import com.example.emtlabgroupb.model.domain.Accommodation;
import com.example.emtlabgroupb.model.domain.Category;
import com.example.emtlabgroupb.model.domain.Condition;
import com.example.emtlabgroupb.model.exception.AccommodationCannotBeDeletedException;
import com.example.emtlabgroupb.model.exception.AccommodationNotInBadConditionException;
import com.example.emtlabgroupb.model.projection.AccommodationExtendedProjection;
import com.example.emtlabgroupb.model.projection.AccommodationShortProjection;
import com.example.emtlabgroupb.repository.AccommodationRepository;
import com.example.emtlabgroupb.service.domain.AccommodationService;
import com.example.emtlabgroupb.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    private final ApplicationEventPublisher eventPublisher;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository,
                                    HostService hostService,
                                    ApplicationEventPublisher eventPublisher) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> findWithHostById(Long id) {
        return accommodationRepository.findWithHostById(id);
    }

    @Override
    public Page<Accommodation> findAll(Pageable pageable) {
        return accommodationRepository.findAll(pageable);
    }

    @Override
    public Page<Accommodation> findFiltered(Category category, Long hostId, String countryName,
                                             Integer numRooms, Boolean hasAvailableRooms, Pageable pageable) {
        return accommodationRepository.findFiltered(category, hostId, countryName, numRooms, hasAvailableRooms, pageable);
    }

    @Override
    public Page<AccommodationShortProjection> findAllShortProjection(Pageable pageable) {
        return accommodationRepository.findAllProjectedBy(pageable);
    }

    @Override
    public Page<AccommodationExtendedProjection> findAllExtendedProjection(Pageable pageable) {
        return accommodationRepository.findAllExtended(pageable);
    }

    @Override
    public Accommodation create(Accommodation accommodation) {
        accommodation.setRented(true);
        return accommodationRepository.save(accommodation);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id)
                .map(a -> {
                    a.setName(accommodation.getName());
                    a.setCategory(accommodation.getCategory());
                    a.setHost(accommodation.getHost());
                    a.setCondition(accommodation.getCondition());
                    a.setNumRooms(accommodation.getNumRooms());
                    return accommodationRepository.save(a);
                });
    }

    @Override
    public Optional<Accommodation> deleteById(Long id) {
        return accommodationRepository.findById(id)
                .map(a -> {
                    if (a.getCondition() != Condition.BAD) throw new AccommodationNotInBadConditionException(id);
                    if (a.getRented()) throw new AccommodationCannotBeDeletedException(id);
                    accommodationRepository.delete(a);
                    return a;
                });
    }

    @Override
    @Transactional
    public Optional<Accommodation> markAsRented(Long id) {
        return accommodationRepository.findById(id)
                .map(a -> {
                    if (a.getNumRooms() > 0) {
                        a.setNumRooms(a.getNumRooms() - 1);
                    }
                    a.setRented(true);
                    Accommodation saved = accommodationRepository.save(a);

                    // Task 7 – publish rental event
                    eventPublisher.publishEvent(new AccommodationRentedEvent(this, saved));

                    // Task 8 – publish fully occupied event if no rooms left
                    if (saved.getNumRooms() == 0) {
                        eventPublisher.publishEvent(new AccommodationFullyOccupiedEvent(this, saved));
                    }

                    return saved;
                });
    }
}
