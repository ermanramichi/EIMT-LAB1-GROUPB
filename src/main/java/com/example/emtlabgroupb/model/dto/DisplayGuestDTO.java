package com.example.emtlabgroupb.model.dto;

import com.example.emtlabgroupb.model.domain.Guest;

public record DisplayGuestDTO(Long id, String name, String email, String passport) {

    public static DisplayGuestDTO from(Guest guest) {
        return new DisplayGuestDTO(
                guest.getId(),
                guest.getName(),
                guest.getEmail(),
                guest.getPassport()
        );
    }
}