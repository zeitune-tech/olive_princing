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
import sn.zeitune.olive_insurance_pricing.app.dtos.ConditionDto;
import sn.zeitune.olive_insurance_pricing.app.services.ConditionService;
import sn.zeitune.olive_insurance_pricing.enums.Operator;

import java.util.List;

@RestController
@RequestMapping("/app/conditions")
@RequiredArgsConstructor
@Slf4j
public class ConditionController {

    private final ConditionService conditionService;

    @PostMapping
    public ResponseEntity<ConditionDto> create(@Valid @RequestBody ConditionDto conditionDto) {
        log.info("REST request to create condition with value: {}", conditionDto.getValue());
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConditionDto> getById(@PathVariable Long id) {
        log.info("REST request to get condition by ID: {}", id);
        return null;
    }

    @GetMapping
    public ResponseEntity<Page<ConditionDto>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        log.info("REST request to get all conditions with pagination");
        return null;
    }

    @GetMapping("/by-value/{value}")
    public ResponseEntity<List<ConditionDto>> getByValue(@PathVariable Double value) {
        log.info("REST request to get conditions by value: {}", value);
        return null;
    }

    @GetMapping("/by-operator/{operator}")
    public ResponseEntity<List<ConditionDto>> getByOperator(@PathVariable Operator operator) {
        log.info("REST request to get conditions by operator: {}", operator);
        return null;
    }

    @GetMapping("/by-field/{fieldId}")
    public ResponseEntity<List<ConditionDto>> getByField(@PathVariable Long fieldId) {
        log.info("REST request to get conditions by field ID: {}", fieldId);
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConditionDto> update(@PathVariable Long id, @Valid @RequestBody ConditionDto conditionDto) {
        log.info("REST request to update condition with ID: {}", id);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request to delete condition with ID: {}", id);
        return null;
    }
}
