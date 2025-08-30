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
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.field.SelectFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.field.SelectFieldService;
import sn.zeitune.olive_insurance_pricing.security.Employee;

import java.util.UUID;

@RestController
@RequestMapping("/app/select-fields")
@RequiredArgsConstructor
@Slf4j
public class SelectFieldController {

    private final SelectFieldService selectFieldService;

    @PostMapping
    public ResponseEntity<SelectFieldResponseDTO> create(
            @Valid @RequestBody SelectFieldRequestDTO selectFieldRequestDTO,
            Authentication authentication
    ) {
        return ResponseEntity.ok(selectFieldService.create(
                selectFieldRequestDTO,
                ((Employee) authentication.getPrincipal()).getManagementEntity())
        );
    }

    @GetMapping
    public ResponseEntity<Page<SelectFieldResponseDTO>> readAll(
            @PageableDefault(size = 20) Pageable pageable,
            Authentication authentication
    ) {
        return ResponseEntity.ok(selectFieldService.retrieveAllActive(
                ((Employee) authentication.getPrincipal()).getManagementEntity(),
                pageable
        ));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<SelectFieldResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody SelectFieldRequestDTO selectFieldRequestDTO) {
//        return ResponseEntity.ok(selectFieldService.updateByUuid(id, selectFieldRequestDTO));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        selectFieldService.delete(id, ((Employee) authentication.getPrincipal()).getManagementEntity());
        return ResponseEntity.ok().build();
    }
}
