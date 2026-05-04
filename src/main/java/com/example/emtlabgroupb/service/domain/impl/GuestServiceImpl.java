package com.example.emtlabgroupb.service.domain.impl;

import com.example.emtlabgroupb.model.domain.Guest;
import com.example.emtlabgroupb.model.domain.Host;
import com.example.emtlabgroupb.model.dto.DisplayGuestDTO;
import com.example.emtlabgroupb.model.dto.GuestDTO;
import com.example.emtlabgroupb.repository.GuestRepository;
import com.example.emtlabgroupb.repository.HostRepository;
import com.example.emtlabgroupb.service.domain.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final HostRepository hostRepository;

    @Override
    public List<DisplayGuestDTO> findAll() {
        return guestRepository.findAll()
                .stream().map(DisplayGuestDTO::from).toList();
    }

    @Override
    public Optional<DisplayGuestDTO> findById(Long id) {
        return guestRepository.findById(id).map(DisplayGuestDTO::from);
    }

    @Override
    public DisplayGuestDTO create(GuestDTO dto) {
        Guest guest = new Guest(dto.name(), dto.email(), dto.passport());
        return DisplayGuestDTO.from(guestRepository.save(guest));
    }

    @Override
    public void delete(Long id) {
        guestRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addGuestToHost(Long guestId, Long hostId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found: " + guestId));
        Host host = hostRepository.findById(hostId)
                .orElseThrow(() -> new RuntimeException("Host not found: " + hostId));
        host.getGuests().add(guest);
        hostRepository.save(host);
    }

    @Override
    public List<DisplayGuestDTO> findGuestsByHost(Long hostId) {
        return guestRepository.findAllByHosts_Id(hostId)
                .stream().map(DisplayGuestDTO::from).toList();
    }
}