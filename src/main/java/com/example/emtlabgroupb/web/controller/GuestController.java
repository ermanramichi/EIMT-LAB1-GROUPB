package com.example.emtlabgroupb.web.controller;

import com.example.emtlabgroupb.model.dto.DisplayGuestDTO;
import com.example.emtlabgroupb.model.dto.GuestDTO;
import com.example.emtlabgroupb.service.domain.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/guests")
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @GetMapping
    @Operation(summary = "Get all guests")
    public List<DisplayGuestDTO> findAll() {
        return guestService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get guest by ID")
    public ResponseEntity<DisplayGuestDTO> findById(@PathVariable Long id) {
        return guestService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a guest")
    public DisplayGuestDTO create(@RequestBody GuestDTO dto) {
        return guestService.create(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a guest")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        guestService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{guestId}/hosts/{hostId}")
    @Operation(summary = "Link a guest to a host")
    public ResponseEntity<Void> addGuestToHost(@PathVariable Long guestId,
                                               @PathVariable Long hostId) {
        guestService.addGuestToHost(guestId, hostId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-host/{hostId}")
    @Operation(summary = "Get all guests who stayed with a specific host")
    public List<DisplayGuestDTO> findByHost(@PathVariable Long hostId) {
        return guestService.findGuestsByHost(hostId);
    }
}