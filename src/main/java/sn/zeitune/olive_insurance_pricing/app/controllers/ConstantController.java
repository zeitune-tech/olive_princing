package sn.zeitune.olive_insurance_pricing.app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.ConstantService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/constants")
@RequiredArgsConstructor
@Slf4j
public class ConstantController {

    private final ConstantService constantService;

    @PostMapping
    public ResponseEntity<ConstantResponseDTO> create(@Valid @RequestBody ConstantRequestDTO constantRequestDTO) {
        log.info("REST request to create constant: {}", constantRequestDTO);
        ConstantResponseDTO response = constantService.create(constantRequestDTO);
        log.info("Created constant with ID: {}", response.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstantResponseDTO> getById(@PathVariable UUID id) {
        log.info("REST request to get constant by ID: {}", id);
        ConstantResponseDTO response = constantService.findByUuid(id);
        log.info("Found constant: {}", response.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ConstantResponseDTO>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        log.info("REST request to get all constants with pagination: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        Page<ConstantResponseDTO> response = constantService.findAll(pageable);
        log.info("Retrieved {} constants out of {} total elements", response.getNumberOfElements(), response.getTotalElements());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-value/{value}")
    public ResponseEntity<List<ConstantResponseDTO>> getByValue(@PathVariable Double value) {
        log.info("REST request to get constants by value: {}", value);
        List<ConstantResponseDTO> response = constantService.findByValue(value);
        log.info("Found {} constants with value: {}", response.size(), value);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-value-range")
    public ResponseEntity<List<ConstantResponseDTO>> getByValueRange(@RequestParam Double minValue, @RequestParam Double maxValue) {
        log.info("REST request to get constants by value range: min={}, max={}", minValue, maxValue);
        // TODO: Implement this method
        log.warn("Method getByValueRange is not implemented yet");
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<List<ConstantResponseDTO>> searchByLabel(@RequestParam String label) {
        log.info("REST request to search constants by label: {}", label);
        List<ConstantResponseDTO> response = constantService.searchByLabel(label);
        log.info("Found {} constants matching label: {}", response.size(), label);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConstantResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody ConstantRequestDTO constantRequestDTO) {
        log.info("REST request to update constant with ID: {}, data: {}", id, constantRequestDTO);
        ConstantResponseDTO response = constantService.updateByUuid(id, constantRequestDTO);
        log.info("Updated constant with ID: {}", response.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        log.info("REST request to delete constant with ID: {}", id);
        constantService.deleteByUuid(id);
        log.info("Successfully deleted constant with ID: {}", id);
        return ResponseEntity.ok().build();
    }
}
