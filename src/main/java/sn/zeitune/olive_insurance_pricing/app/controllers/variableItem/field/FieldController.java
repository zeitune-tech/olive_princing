package sn.zeitune.olive_insurance_pricing.app.controllers.variableItem.field;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.field.FieldService;
import sn.zeitune.olive_insurance_pricing.security.Employee;

import java.util.UUID;

@RestController
@RequestMapping("/app/fields")
@RequiredArgsConstructor
@Slf4j
public class FieldController {

    private final FieldService fieldService;

    @GetMapping("/{id}")
    public ResponseEntity<FieldResponseDTO> readById(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        return ResponseEntity.ok(fieldService.retrieveActive(id, ((Employee) authentication.getPrincipal()).getManagementEntity()));
    }

    @GetMapping
    public ResponseEntity<Page<FieldResponseDTO>> readAll(
            @PageableDefault(size = 20) Pageable pageable,
            Authentication authentication
    ) {
        return ResponseEntity.ok(fieldService.retrieveAllActive(
                ((Employee) authentication.getPrincipal()).getManagementEntity(),
                pageable
        ));
    }

}
