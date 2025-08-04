package sn.zeitune.olive_insurance_pricing.app.controllers.field;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.FieldService;
import sn.zeitune.olive_insurance_pricing.security.Employee;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/fields")
@RequiredArgsConstructor
@Slf4j
public class FieldController {

    private final FieldService fieldService;

    @GetMapping("/{id}")
    public ResponseEntity<FieldResponseDTO> getById(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        return ResponseEntity.ok(fieldService.findByUuid(id, ((Employee) authentication.getPrincipal()).getManagementEntity()));
    }

    @GetMapping
    public ResponseEntity<Page<FieldResponseDTO>> getAll(
            @PageableDefault(size = 20) Pageable pageable,
            Authentication authentication
    ) {
        return ResponseEntity.ok(fieldService.findAll(
                pageable,
                ((Employee) authentication.getPrincipal()).getManagementEntity()
        ));
    }

//    @GetMapping("/by-product/{product}")
//    public ResponseEntity<List<FieldResponseDTO>> getByProduct(@PathVariable UUID product) {
//        return ResponseEntity.ok(fieldService.findByProduct(product));
//    }

//    @GetMapping("/search")
//    public ResponseEntity<List<FieldResponseDTO>> searchByLabel(@RequestParam String label) {
//        return ResponseEntity.ok(fieldService.searchByLabel(label));
//    }

}
