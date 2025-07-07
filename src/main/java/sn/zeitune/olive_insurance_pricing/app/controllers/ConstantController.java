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
        return ResponseEntity.ok(constantService.create(constantRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstantResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(constantService.findByUuid(id));
    }

    @GetMapping
    public ResponseEntity<Page<ConstantResponseDTO>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(constantService.findAll(pageable));
    }

    @GetMapping("/by-value/{value}")
    public ResponseEntity<List<ConstantResponseDTO>> getByValue(@PathVariable Double value) {
        return null;
    }

    @GetMapping("/by-value-range")
    public ResponseEntity<List<ConstantResponseDTO>> getByValueRange(@RequestParam Double minValue, @RequestParam Double maxValue) {
        return null;
    }

    @GetMapping("/by-product/{product}")
    public ResponseEntity<List<ConstantResponseDTO>> getByProduct(@PathVariable UUID product) {
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<List<ConstantResponseDTO>> searchByLabel(@RequestParam String label) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConstantResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ConstantRequestDTO constantRequestDTO) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request to delete constant with ID: {}", id);
        return null;
    }
}
