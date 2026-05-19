package com.example.emtlabgroupb.web.controller;

import com.example.emtlabgroupb.model.dto.CreateCountryDto;
import com.example.emtlabgroupb.model.dto.DisplayCountryDto;
import com.example.emtlabgroupb.service.domain.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Countries", description = "Endpoints for managing countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    @Operation(summary = "Get all countries")
    public List<DisplayCountryDto> findAll() {
        return countryService.findAll().stream()
                .map(DisplayCountryDto::from)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get country by ID")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryService.findById(id)
                .map(DisplayCountryDto::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Create a new country")
    public ResponseEntity<DisplayCountryDto> create(@Valid @RequestBody CreateCountryDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DisplayCountryDto.from(countryService.save(dto)));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update an existing country")
    public ResponseEntity<DisplayCountryDto> update(@PathVariable Long id,
                                                    @Valid @RequestBody CreateCountryDto dto) {
        return countryService.update(id, dto)
                .map(DisplayCountryDto::from)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a country")
    public ResponseEntity<DisplayCountryDto> delete(@PathVariable Long id) {
        return countryService.deleteById(id)
                .map(DisplayCountryDto::from)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
