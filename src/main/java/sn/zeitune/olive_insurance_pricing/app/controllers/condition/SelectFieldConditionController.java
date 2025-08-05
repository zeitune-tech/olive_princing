package sn.zeitune.olive_insurance_pricing.app.controllers.condition;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.SelectConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.SelectConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldConditionService;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldOptionValueService;
import sn.zeitune.olive_insurance_pricing.enums.NumericOperator;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/select-field-conditions")
@RequiredArgsConstructor
@Slf4j
public class SelectFieldConditionController {

    private final SelectFieldConditionService selectFieldConditionService;
    private final SelectFieldOptionValueService selectFieldOptionValueService;

    @PostMapping
    public ResponseEntity<SelectConditionResponseDTO> create(@Valid @RequestBody SelectConditionRequestDTO selectConditionRequestDTO) {
        log.info("REST request to create condition with value: {}", selectConditionRequestDTO.getValue());
        return ResponseEntity.ok(selectFieldConditionService.create(selectConditionRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SelectConditionResponseDTO> getById(@PathVariable Long id) {
        log.info("REST request to get condition by ID: {}", id);
        return null;
    }

    @GetMapping
    public ResponseEntity<Page<SelectConditionResponseDTO>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        log.info("REST request to get all conditions with pagination");
        return ResponseEntity.ok(selectFieldConditionService.findAll(pageable));
    }

    @GetMapping("/by-value/{value}")
    public ResponseEntity<List<SelectConditionResponseDTO>> getByValue(@PathVariable UUID value) {
        log.info("REST request to get conditions by value: {}", value);
        SelectFieldOptionValue optionValue = selectFieldOptionValueService.getEntityByUuid(value);
        return ResponseEntity.ok(selectFieldConditionService.findByValue(optionValue));
    }

    @GetMapping("/by-operator/{selectOperator}")
    public ResponseEntity<List<SelectConditionResponseDTO>> getByOperator(@PathVariable NumericOperator selectOperator) {
        log.info("REST request to get conditions by operator: {}", selectOperator);
        return null;
    }

    @GetMapping("/by-field/{fieldId}")
    public ResponseEntity<List<SelectConditionResponseDTO>> getByField(@PathVariable UUID fieldId) {
        log.info("REST request to get conditions by field ID: {}", fieldId);
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<SelectConditionResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody SelectConditionRequestDTO selectConditionRequestDTO) {
        log.info("REST request to update condition with ID: {}", id);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        log.info("REST request to delete condition with ID: {}", id);
        return null;
    }
}
