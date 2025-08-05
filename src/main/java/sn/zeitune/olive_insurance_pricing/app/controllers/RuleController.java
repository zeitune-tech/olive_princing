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
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.RuleRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.RuleResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.RuleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/rules")
@RequiredArgsConstructor
@Slf4j
public class RuleController {

    private final RuleService ruleService;

    @PostMapping
    public ResponseEntity<RuleResponseDTO> create(@Valid @RequestBody RuleRequestDTO ruleRequestDTO) {
        log.info("REST request to create rule: {}", ruleRequestDTO.getLabel());
        return ResponseEntity.ok(ruleService.create(ruleRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RuleResponseDTO> getById(@PathVariable Long id) {
        log.info("REST request to get rule by ID: {}", id);
        return null;
    }

    @GetMapping
    public ResponseEntity<Page<RuleResponseDTO>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        log.info("REST request to get all rules with pagination");
        return ResponseEntity.ok(ruleService.findAll(pageable));
    }

//    @GetMapping("/by-value/{value}")
//    public ResponseEntity<List<RuleResponseDTO>> getByValue(@PathVariable Double value) {
//        log.info("REST request to get rules by value: {}", value);
//        return null;
//    }
//
//    @GetMapping("/by-product/{product}")
//    public ResponseEntity<List<RuleResponseDTO>> getByProduct(@PathVariable UUID product) {
//        log.info("REST request to get rules by product: {}", product);
//        return null;
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<List<RuleResponseDTO>> searchByLabel(@RequestParam String label) {
//        log.info("REST request to search rules by label: {}", label);
//        return null;
//    }

    @PutMapping("/{id}")
    public ResponseEntity<RuleResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody RuleRequestDTO ruleRequestDTO) {
        log.info("REST request to update rule with ID: {}", id);
        return ResponseEntity.ok(ruleService.updateByUuid(id, ruleRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        log.info("REST request to delete rule with ID: {}", id);
        ruleService.deleteByUuid(id);
        return ResponseEntity.ok().build();
    }
}
