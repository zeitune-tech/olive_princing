package sn.zeitune.olive_insurance_pricing.app.controllers.variableItem.variableCondition;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition.VariableConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition.VariableConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.variableCondition.VariableConditionService;
import sn.zeitune.olive_insurance_pricing.security.Employee;

import java.util.UUID;

@RestController
@RequestMapping("/app/variable-conditions")
@RequiredArgsConstructor
@Slf4j
public class VariableConditionController {

    private final VariableConditionService variableConditionService;

    @PostMapping
    public ResponseEntity<VariableConditionResponseDTO> create(
            @Valid @RequestBody VariableConditionRequestDTO variableConditionRequestDTO,
            Authentication authentication
    ) {
        log.info("REST request to create variable condition: {}", variableConditionRequestDTO.getLabel());
        return ResponseEntity.ok(variableConditionService.create(variableConditionRequestDTO, ((Employee) authentication.getPrincipal()).getManagementEntity()));
    }

    @GetMapping
    public ResponseEntity<Page<VariableConditionResponseDTO>> readAll
            (
                    @PageableDefault(size = 20) Pageable pageable,
                    Authentication authentication
            ) {
        log.info("REST request to get all variable conditions with pagination");
        return ResponseEntity.ok(variableConditionService.retrieveAllActive(
                ((Employee) authentication.getPrincipal()).getManagementEntity()
                , pageable
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VariableConditionResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody VariableConditionRequestDTO variableConditionRequestDTO,
            Authentication authentication
    ) {
        log.info("REST request to update variable condition with ID: {}", id);
        return ResponseEntity.ok(variableConditionService.update(
                id,
                variableConditionRequestDTO,
                ((Employee) authentication.getPrincipal()).getManagementEntity()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        log.info("REST request to delete variable condition with ID: {}", id);
        variableConditionService.delete(
                id,
                ((Employee) authentication.getPrincipal()).getManagementEntity()
        );
        return ResponseEntity.ok().build();
    }
}
