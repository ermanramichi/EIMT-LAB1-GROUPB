package com.example.emtlabgroupb.model.exception;

public class AccommodationNotInBadConditionException extends RuntimeException {

    public AccommodationNotInBadConditionException(Long id) {
        super("Accommodation with id " + id + " cannot be deleted because it is not in BAD condition.");
    }
}
