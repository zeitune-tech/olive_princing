package sn.zeitune.olive_insurance_pricing.app.controllers.condition;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.NumericalConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.NumericalConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.NumericalConditionService;
import sn.zeitune.olive_insurance_pricing.enums.NumericOperator;

import java.util.List;

@RestController
@RequestMapping("/app/numerical-conditions")
@RequiredArgsConstructor
@Slf4j
public class NumericalConditionController {

    private final NumericalConditionService numericalConditionService;

    @PostMapping
    public ResponseEntity<NumericalConditionResponseDTO> create(@Valid @RequestBody NumericalConditionRequestDTO numericalConditionRequestDTO) {
        log.info("REST request to create condition with value: {}", numericalConditionRequestDTO.value());
        return ResponseEntity.ok(numericalConditionService.create(numericalConditionRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NumericalConditionResponseDTO> getById(@PathVariable Long id) {
        log.info("REST request to get condition by ID: {}", id);
        return null;
    }

    @GetMapping
    public ResponseEntity<Page<NumericalConditionResponseDTO>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        log.info("REST request to get all conditions with pagination");
        return ResponseEntity.ok(numericalConditionService.findAll(pageable));
    }

    @GetMapping("/by-value/{value}")
    public ResponseEntity<List<NumericalConditionResponseDTO>> getByValue(@PathVariable Double value) {
        log.info("REST request to get conditions by value: {}", value);
        return null;
    }

    @GetMapping("/by-operator/{numericOperator}")
    public ResponseEntity<List<NumericalConditionResponseDTO>> getByOperator(@PathVariable NumericOperator numericOperator) {
        log.info("REST request to get conditions by operator: {}", numericOperator);
        return null;
    }

    @GetMapping("/by-field/{fieldId}")
    public ResponseEntity<List<NumericalConditionResponseDTO>> getByField(@PathVariable Long fieldId) {
        log.info("REST request to get conditions by field ID: {}", fieldId);
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<NumericalConditionResponseDTO> update(@PathVariable Long id, @Valid @RequestBody NumericalConditionRequestDTO numericalConditionRequestDTO) {
        log.info("REST request to update condition with ID: {}", id);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request to delete condition with ID: {}", id);
        return null;
    }
}
