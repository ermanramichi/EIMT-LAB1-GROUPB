package com.example.emtlabgroupb.service.domain.impl;

import com.example.emtlabgroupb.model.domain.Accommodation;
import com.example.emtlabgroupb.model.domain.Condition;
import com.example.emtlabgroupb.model.domain.Host;
import com.example.emtlabgroupb.model.exception.AccommodationCannotBeDeletedException;
import com.example.emtlabgroupb.model.exception.AccommodationNotInBadConditionException;
import com.example.emtlabgroupb.repository.AccommodationRepository;
import com.example.emtlabgroupb.service.domain.AccommodationService;
import com.example.emtlabgroupb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
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
                    if(a.getCondition() != Condition.BAD) throw new AccommodationNotInBadConditionException(id);

                    if (a.getRented()) throw new AccommodationCannotBeDeletedException(id);

                    accommodationRepository.delete(a);

                    return a;

                });
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
        return accommodationRepository.findById(id)
                .map(a -> {
                    a.setRented(true);

                    return accommodationRepository.save(a);
                });
    }
}
