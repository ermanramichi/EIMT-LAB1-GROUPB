package com.example.emtlabgroupb.web.controller;

import com.example.emtlabgroupb.model.dto.DisplayHostDto;
import com.example.emtlabgroupb.service.domain.HostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Hosts", description = "Endpoints for managing hosts")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    @Operation(summary = "Get all hosts")
    public List<DisplayHostDto> findAll() {
        return hostService.findAll().stream()
                .map(DisplayHostDto::from)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get host by ID")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostService.findById(id)
                .map(DisplayHostDto::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
