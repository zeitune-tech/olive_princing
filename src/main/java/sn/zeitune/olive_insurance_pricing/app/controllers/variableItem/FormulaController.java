package sn.zeitune.olive_insurance_pricing.app.controllers.variableItem;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.FormulaRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.FormulaService;
import sn.zeitune.olive_insurance_pricing.security.Employee;

import java.util.UUID;

@RestController
@RequestMapping("/app/formulas")
@RequiredArgsConstructor
@Slf4j
public class FormulaController {

    private final FormulaService formulaService;

    @PostMapping
    public ResponseEntity<FormulaResponseDTO> create(
            @Valid @RequestBody FormulaRequestDTO formulaDto,
            Authentication authentication
    ) {
        log.info("REST request to create formula: {}", formulaDto.getLabel());
        return ResponseEntity.ok(formulaService.create(formulaDto, ((Employee) authentication.getPrincipal()).getManagementEntity()));
    }

    @GetMapping
    public ResponseEntity<Page<FormulaResponseDTO>> readAll(
            @PageableDefault(size = 20) Pageable pageable,
            Authentication authentication
    ) {
        log.info("REST request to get all formulas with pagination");
        return ResponseEntity.ok(formulaService.retrieveAllActive(
                ((Employee) authentication.getPrincipal()).getManagementEntity(),
                pageable
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormulaResponseDTO> update(
            @PathVariable UUID id, @Valid @RequestBody FormulaRequestDTO formulaDto,
            Authentication authentication
    ) {
        log.info("REST request to update formula with ID: {}", id);
        return ResponseEntity.ok(formulaService.update(
                id,
                formulaDto,
                ((Employee) authentication.getPrincipal()).getManagementEntity()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        log.info("REST request to delete formula with ID: {}", id);
        formulaService.delete(id, ((Employee) authentication.getPrincipal()).getManagementEntity());
        return ResponseEntity.ok().build();
    }
}
