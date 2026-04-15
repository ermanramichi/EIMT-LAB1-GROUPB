package com.example.emtlabgroupb.listeners;

import com.example.emtlabgroupb.events.AccommodationRentedEvent;
import com.example.emtlabgroupb.model.domain.ActivityLog;
import com.example.emtlabgroupb.model.domain.Accommodation;
import com.example.emtlabgroupb.repository.ActivityLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AccommodationRentedListener {

    private static final Logger log = LoggerFactory.getLogger(AccommodationRentedListener.class);
    private final ActivityLogRepository activityLogRepository;

    public AccommodationRentedListener(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @EventListener
    public void onAccommodationRented(AccommodationRentedEvent event) {
        Accommodation accommodation = event.getAccommodation();

        log.info("[RENTAL EVENT] Accommodation '{}' (id={}) has been rented. Remaining rooms: {}",
                accommodation.getName(), accommodation.getId(), accommodation.getNumRooms());

        ActivityLog logEntry = new ActivityLog(
                accommodation.getName(),
                LocalDateTime.now(),
                "ACCOMMODATION_RENTED",
                accommodation.getId(),
                accommodation.getHost().getId()
        );
        activityLogRepository.save(logEntry);
    }
}