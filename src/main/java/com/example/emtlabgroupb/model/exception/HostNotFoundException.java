package com.example.emtlabgroupb.model.exception;

public class HostNotFoundException extends RuntimeException {

    public HostNotFoundException(Long id) {
        super("Host with id " + id + " was not found.");
    }
}
