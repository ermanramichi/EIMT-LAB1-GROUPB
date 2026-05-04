package com.example.emtlabgroupb.web.handler;

import com.example.emtlabgroupb.model.exception.AccommodationCannotBeDeletedException;
import com.example.emtlabgroupb.model.exception.AccommodationNotFoundException;
import com.example.emtlabgroupb.model.exception.AccommodationNotInBadConditionException;
import com.example.emtlabgroupb.model.exception.HostNotFoundException;
import com.example.emtlabgroupb.web.controller.AccommodationController;
import com.example.emtlabgroupb.web.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = AccommodationController.class)
public class AccommodationControllerHandler {

    @ExceptionHandler(AccommodationCannotBeDeletedException.class)
    public ResponseEntity<ApiError> handleAccommodationCannotBeDeleted(AccommodationCannotBeDeletedException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiError.of(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    @ExceptionHandler(AccommodationNotFoundException.class)
    public ResponseEntity<ApiError> handleAccommodationNotFound(AccommodationNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiError.of(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(AccommodationNotInBadConditionException.class)
    public ResponseEntity<ApiError> handleAccommodationNotInBadCondition(AccommodationNotInBadConditionException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiError.of(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    @ExceptionHandler(HostNotFoundException.class)
    public ResponseEntity<ApiError> handleeHostNotFound(HostNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiError.of(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

}
