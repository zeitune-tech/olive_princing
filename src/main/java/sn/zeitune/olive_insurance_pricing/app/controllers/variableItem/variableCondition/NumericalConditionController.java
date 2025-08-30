package sn.zeitune.olive_insurance_pricing.app.controllers.variableItem.variableCondition;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition.NumericConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition.NumericConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.variableCondition.NumericalConditionService;

import java.util.UUID;

@RestController
@RequestMapping("/app/numeric-conditions")
@RequiredArgsConstructor
@Slf4j
public class NumericalConditionController {

    private final NumericalConditionService numericalConditionService;

    @PostMapping
    public ResponseEntity<NumericConditionResponseDTO> create(@Valid @RequestBody NumericConditionRequestDTO numericConditionRequestDTO) {
        log.info("REST request to create condition with value: {}", numericConditionRequestDTO.getValue());
        return ResponseEntity.ok(numericalConditionService.create(numericConditionRequestDTO));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<NumericConditionResponseDTO> getById(@PathVariable Long id) {
//        log.info("REST request to get condition by ID: {}", id);
//        return null;
//    }

    @GetMapping
    public ResponseEntity<Page<NumericConditionResponseDTO>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        log.info("REST request to get all conditions with pagination");
        return ResponseEntity.ok(numericalConditionService.findAll(pageable));
    }

//    @GetMapping("/by-value/{value}")
//    public ResponseEntity<List<NumericConditionResponseDTO>> getByValue(@PathVariable Double value) {
//        log.info("REST request to get conditions by value: {}", value);
//        return null;
//    }
//
//    @GetMapping("/by-operator/{numericOperator}")
//    public ResponseEntity<List<NumericConditionResponseDTO>> getByOperator(@PathVariable NumericOperator numericOperator) {
//        log.info("REST request to get conditions by operator: {}", numericOperator);
//        return null;
//    }
//
//    @GetMapping("/by-field/{fieldId}")
//    public ResponseEntity<List<NumericConditionResponseDTO>> getByField(@PathVariable Long fieldId) {
//        log.info("REST request to get conditions by field ID: {}", fieldId);
//        return null;
//    }

    @PutMapping("/{id}")
    public ResponseEntity<NumericConditionResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody NumericConditionRequestDTO numericConditionRequestDTO) {
        log.info("REST request to update condition with ID: {}", id);
        return ResponseEntity.ok(numericalConditionService.updateByUuid(id, numericConditionRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        log.info("REST request to delete condition with ID: {}", id);
        numericalConditionService.deleteByUuid(id);
        return ResponseEntity.ok().build();
    }
}
