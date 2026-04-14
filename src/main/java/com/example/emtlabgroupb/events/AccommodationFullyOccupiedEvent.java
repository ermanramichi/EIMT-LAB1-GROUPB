package com.example.emtlabgroupb.events;

import com.example.emtlabgroupb.model.domain.Accommodation;
import org.springframework.context.ApplicationEvent;

public class AccommodationFullyOccupiedEvent extends ApplicationEvent {

    private final Accommodation accommodation;

    public AccommodationFullyOccupiedEvent(Object source, Accommodation accommodation) {
        super(source);
        this.accommodation = accommodation;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }
}
