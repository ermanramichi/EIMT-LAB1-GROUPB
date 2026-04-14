package com.example.emtlabgroupb.events;

import com.example.emtlabgroupb.model.domain.Accommodation;
import org.springframework.context.ApplicationEvent;

public class AccommodationRentedEvent extends ApplicationEvent {

    private final Accommodation accommodation;

    public AccommodationRentedEvent(Object source, Accommodation accommodation) {
        super(source);
        this.accommodation = accommodation;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }
}
