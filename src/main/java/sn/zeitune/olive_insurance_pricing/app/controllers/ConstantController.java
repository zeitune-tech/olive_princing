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
import sn.zeitune.olive_insurance_pricing.app.dtos.ConstantDto;
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
    public ResponseEntity<ConstantDto> create(@Valid @RequestBody ConstantDto constantDto) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstantDto> getById(@PathVariable Long id) {
        return null;
    }

    @GetMapping
    public ResponseEntity<Page<ConstantDto>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return null;
    }

    @GetMapping("/by-value/{value}")
    public ResponseEntity<List<ConstantDto>> getByValue(@PathVariable Double value) {
        return null;
    }

    @GetMapping("/by-value-range")
    public ResponseEntity<List<ConstantDto>> getByValueRange(@RequestParam Double minValue, @RequestParam Double maxValue) {
        return null;
    }

    @GetMapping("/by-product/{product}")
    public ResponseEntity<List<ConstantDto>> getByProduct(@PathVariable UUID product) {
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<List<ConstantDto>> searchByLabel(@RequestParam String label) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConstantDto> update(@PathVariable Long id, @Valid @RequestBody ConstantDto constantDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request to delete constant with ID: {}", id);
        return null;
    }
}
