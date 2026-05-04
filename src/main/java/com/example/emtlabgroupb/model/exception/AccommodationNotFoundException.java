package com.example.emtlabgroupb.model.exception;

public class AccommodationNotFoundException extends RuntimeException {

    public AccommodationNotFoundException(Long id) {
        super("Accommodation with id " + id + " was not found.");
    }
}
