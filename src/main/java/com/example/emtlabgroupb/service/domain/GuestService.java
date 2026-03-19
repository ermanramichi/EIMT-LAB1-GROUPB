package com.example.emtlabgroupb.service.domain;

import com.example.emtlabgroupb.model.dto.DisplayGuestDTO;
import com.example.emtlabgroupb.model.dto.GuestDTO;
import java.util.List;
import java.util.Optional;

public interface GuestService {
    List<DisplayGuestDTO> findAll();
    Optional<DisplayGuestDTO> findById(Long id);
    DisplayGuestDTO create(GuestDTO dto);
    void delete(Long id);
    void addGuestToHost(Long guestId, Long hostId);
    List<DisplayGuestDTO> findGuestsByHost(Long hostId);
}