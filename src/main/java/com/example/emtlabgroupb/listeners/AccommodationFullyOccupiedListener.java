package com.example.emtlabgroupb.listeners;

import com.example.emtlabgroupb.events.AccommodationFullyOccupiedEvent;
import com.example.emtlabgroupb.model.domain.ActivityLog;
import com.example.emtlabgroupb.model.domain.Accommodation;
import com.example.emtlabgroupb.repository.ActivityLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AccommodationFullyOccupiedListener {

    private static final Logger log = LoggerFactory.getLogger(AccommodationFullyOccupiedListener.class);
    private final ActivityLogRepository activityLogRepository;

    public AccommodationFullyOccupiedListener(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @EventListener
    public void onAccommodationFullyOccupied(AccommodationFullyOccupiedEvent event) {
        Accommodation accommodation = event.getAccommodation();

        log.warn("[FULLY OCCUPIED] Accommodation '{}' (id={}) has NO available rooms left!",
                accommodation.getName(), accommodation.getId());

        ActivityLog logEntry = new ActivityLog(
                accommodation.getName(),
                LocalDateTime.now(),
                "ACCOMMODATION_FULLY_OCCUPIED",
                accommodation.getId(),
                accommodation.getHost().getId()
        );
        activityLogRepository.save(logEntry);
    }
}
