package com.example.emtlabgroupb.web.controller;

import com.example.emtlabgroupb.model.domain.ActivityLog;
import com.example.emtlabgroupb.model.domain.Category;
import com.example.emtlabgroupb.model.dto.CreateAccommodationDto;
import com.example.emtlabgroupb.model.dto.DisplayAccommodationDto;
import com.example.emtlabgroupb.model.dto.PopularAccommodationDto;
import com.example.emtlabgroupb.model.dto.PopularHostDto;
import com.example.emtlabgroupb.model.projection.AccommodationExtendedProjection;
import com.example.emtlabgroupb.model.projection.AccommodationShortProjection;
import com.example.emtlabgroupb.model.views.AccommodationViewEntity;
import com.example.emtlabgroupb.model.views.CategoryStatsEntity;
import com.example.emtlabgroupb.service.application.AccommodationApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodations", description = "Endpoints for managing accommodations")
public class AccommodationController {

    private final AccommodationApplicationService accommodationApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }

    // ── Task 1: Paginated + filtered listing ─────────────────────────────────

    @GetMapping
    @Operation(summary = "List accommodations with pagination, sorting and filters")
    public Page<DisplayAccommodationDto> findAll(
            @Parameter(description = "Filter by category") @RequestParam(required = false) Category category,
            @Parameter(description = "Filter by host ID") @RequestParam(required = false) Long hostId,
            @Parameter(description = "Filter by host's country name") @RequestParam(required = false) String countryName,
            @Parameter(description = "Filter by number of rooms") @RequestParam(required = false) Integer numRooms,
            @Parameter(description = "Filter by availability (true = has rooms)") @RequestParam(required = false) Boolean hasAvailableRooms,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort field: name | createdAt") @RequestParam(defaultValue = "name") String sortBy,
            @Parameter(description = "Sort direction: asc | desc") @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        boolean hasFilters = category != null || hostId != null || countryName != null
                || numRooms != null || hasAvailableRooms != null;

        if (hasFilters) {
            return accommodationApplicationService.findFiltered(category, hostId, countryName, numRooms, hasAvailableRooms, pageable);
        }
        return accommodationApplicationService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get accommodation by ID (uses EntityGraph to load host + country)")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        // Task 3: uses EntityGraph-backed method
        return accommodationApplicationService.findWithHostById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ── Task 2: Projection endpoints ──────────────────────────────────────────

    @GetMapping("/projection/short")
    @Operation(summary = "List accommodations – short projection (id, name, category, numRooms)")
    public Page<AccommodationShortProjection> findAllShort(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return accommodationApplicationService.findAllShortProjection(PageRequest.of(page, size));
    }

    @GetMapping("/projection/extended")
    @Operation(summary = "List accommodations – extended projection (includes host name and country)")
    public Page<AccommodationExtendedProjection> findAllExtended(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return accommodationApplicationService.findAllExtendedProjection(PageRequest.of(page, size));
    }

    // ── Task 4: DB View endpoint ──────────────────────────────────────────────

    @GetMapping("/view")
    @Operation(summary = "List accommodations from the database view (accommodation_view)")
    public Page<AccommodationViewEntity> findAllFromView(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return accommodationApplicationService.findAllFromView(PageRequest.of(page, size));
    }

    // ── Task 5: Materialized View endpoint ───────────────────────────────────

    @GetMapping("/stats/category")
    @Operation(summary = "Category statistics from the materialized view (category_stats)")
    public Page<CategoryStatsEntity> findCategoryStats(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return accommodationApplicationService.findCategoryStats(PageRequest.of(page, size));
    }

    // ── Task 9: Activity log endpoint ────────────────────────────────────────

    @GetMapping("/activity-log")
    @Operation(summary = "View activity log for accommodation rental events (paginated)")
    public Page<ActivityLog> findActivityLog(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return accommodationApplicationService.findActivityLog(PageRequest.of(page, size));
    }

    // ── CRUD ─────────────────────────────────────────────────────────────────

    @PostMapping("/add")
    @Operation(summary = "Create a new accommodation")
    public ResponseEntity<DisplayAccommodationDto> create(@Valid @RequestBody CreateAccommodationDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accommodationApplicationService.create(dto));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update an existing accommodation")
    public ResponseEntity<DisplayAccommodationDto> update(
            @PathVariable Long id,
            @Valid @RequestBody CreateAccommodationDto dto
    ) {
        return accommodationApplicationService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an accommodation (must be in BAD condition and not rented)")
    public ResponseEntity<DisplayAccommodationDto> delete(@PathVariable Long id) {
        return accommodationApplicationService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/rent/{id}")
    @Operation(summary = "Mark accommodation as rented (decrements numRooms, fires rental event)")
    public ResponseEntity<DisplayAccommodationDto> markAsRented(@PathVariable Long id) {
        return accommodationApplicationService.markAsRented(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/popular/accommodations")
    @Operation(summary = "Most popular accommodations ranked by number of bookings")
    public Page<PopularAccommodationDto> findMostPopularAccommodations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return accommodationApplicationService.findMostPopularAccommodations(PageRequest.of(page, size));
    }

    @GetMapping("/popular/hosts")
    @Operation(summary = "Most popular hosts ranked by total bookings across their accommodations")
    public Page<PopularHostDto> findMostPopularHosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return accommodationApplicationService.findMostPopularHosts(PageRequest.of(page, size));
    }
}
