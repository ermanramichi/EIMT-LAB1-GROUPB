package com.example.emtlabgroupb.model.exception;

public class AccommodationCannotBeDeletedException extends RuntimeException {

    public AccommodationCannotBeDeletedException(Long id) {
        super("Accommodation with id " + id + " cannot be deleted because it is currently rented.");
    }
}
