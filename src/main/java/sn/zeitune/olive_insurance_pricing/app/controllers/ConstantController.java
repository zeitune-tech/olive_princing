package sn.zeitune.olive_insurance_pricing.app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.ConstantService;
import sn.zeitune.olive_insurance_pricing.security.Employee;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/constants")
@RequiredArgsConstructor
@Slf4j
public class ConstantController {

    private final ConstantService constantService;

    @PostMapping
    public ResponseEntity<ConstantResponseDTO> create(
            @Valid @RequestBody ConstantRequestDTO constantRequestDTO,
            Authentication authentication
    ) {
        log.info("REST request to create constant: {}", constantRequestDTO);
        ConstantResponseDTO response = constantService.create(
                constantRequestDTO,
                ((Employee) authentication.getPrincipal()).getManagementEntity()
        );
        log.info("Created constant with ID: {}", response.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ConstantResponseDTO>> getAll(
            @PageableDefault(size = 20) Pageable pageable,
            Authentication authentication
    ) {
        log.info("REST request to get all constants with pagination: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        Page<ConstantResponseDTO> response = constantService.retrieveAll(((Employee) authentication.getPrincipal()).getManagementEntity(), pageable);
        log.info("Retrieved {} constants out of {} total elements", response.getNumberOfElements(), response.getTotalElements());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConstantResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody ConstantRequestDTO constantRequestDTO,
            Authentication authentication
    ) {
        log.info("REST request to update constant with ID: {}, data: {}", id, constantRequestDTO);
        ConstantResponseDTO response = constantService.update(id, constantRequestDTO, ((Employee) authentication.getPrincipal()).getManagementEntity());
        log.info("Updated constant with ID: {}", response.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        log.info("REST request to delete constant with ID: {}", id);
        constantService.delete(id, ((Employee) authentication.getPrincipal()).getManagementEntity());
        log.info("Successfully deleted constant with ID: {}", id);
        return ResponseEntity.ok().build();
    }
}
