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
import sn.zeitune.olive_insurance_pricing.app.dtos.VariableConditionDto;
import sn.zeitune.olive_insurance_pricing.app.services.VariableConditionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/variable-conditions")
@RequiredArgsConstructor
@Slf4j
public class VariableConditionController {

    private final VariableConditionService variableConditionService;

    @PostMapping
    public ResponseEntity<VariableConditionDto> create(@Valid @RequestBody VariableConditionDto variableConditionDto) {
        log.info("REST request to create variable condition: {}", variableConditionDto.getLabel());
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VariableConditionDto> getById(@PathVariable Long id) {
        log.info("REST request to get variable condition by ID: {}", id);
        return null;
    }

    @GetMapping
    public ResponseEntity<Page<VariableConditionDto>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        log.info("REST request to get all variable conditions with pagination");
        return null;
    }

    @GetMapping("/by-product/{product}")
    public ResponseEntity<List<VariableConditionDto>> getByProduct(@PathVariable UUID product) {
        log.info("REST request to get variable conditions by product: {}", product);
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<List<VariableConditionDto>> searchByLabel(@RequestParam String label) {
        log.info("REST request to search variable conditions by label: {}", label);
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<VariableConditionDto> update(@PathVariable Long id, @Valid @RequestBody VariableConditionDto variableConditionDto) {
        log.info("REST request to update variable condition with ID: {}", id);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request to delete variable condition with ID: {}", id);
        return null;
    }
}
