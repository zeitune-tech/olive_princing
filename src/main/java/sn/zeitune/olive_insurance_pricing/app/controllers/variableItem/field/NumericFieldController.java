package sn.zeitune.olive_insurance_pricing.app.controllers.variableItem.field;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.field.NumericFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.field.NumericFieldService;
import sn.zeitune.olive_insurance_pricing.security.Employee;

import java.util.UUID;

@RestController
@RequestMapping("/app/numeric-fields")
@RequiredArgsConstructor
@Slf4j
public class NumericFieldController {

    private final NumericFieldService numericFieldService;

    @PostMapping
    public ResponseEntity<NumericFieldResponseDTO> create(
            @Valid @RequestBody NumericFieldRequestDTO selectFieldRequestDTO,
            Authentication authentication
    ) {
        return ResponseEntity.ok(numericFieldService.create(selectFieldRequestDTO, ((Employee) authentication.getPrincipal()).getManagementEntity()));
    }

    @GetMapping
    public ResponseEntity<Page<NumericFieldResponseDTO>> readAll(
            @PageableDefault(size = 20) Pageable pageable,
            Authentication authentication
    ) {
        return ResponseEntity.ok(numericFieldService.retrieveAllActive(
                ((Employee) authentication.getPrincipal()).getManagementEntity()
                , pageable)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<NumericFieldResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody NumericFieldRequestDTO NumericFieldRequestDTO,
            Authentication authentication
    ) {
        return ResponseEntity.ok(numericFieldService.update(id, NumericFieldRequestDTO, ((Employee) authentication.getPrincipal()).getManagementEntity()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        numericFieldService.delete(id, ((Employee) authentication.getPrincipal()).getManagementEntity());
        return ResponseEntity.ok().build();
    }
}
