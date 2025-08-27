package sn.zeitune.olive_insurance_pricing.app.controllers.field;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.NumericFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.NumericFieldService;
import sn.zeitune.olive_insurance_pricing.security.Employee;

import java.util.List;
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
        return ResponseEntity.ok(numericFieldService.create(selectFieldRequestDTO, ((Employee)authentication.getPrincipal()).getManagementEntity()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NumericFieldResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(numericFieldService.findByUuid(id));
    }

    @GetMapping
    public ResponseEntity<Page<NumericFieldResponseDTO>> getAll(
            @PageableDefault(size = 20) Pageable pageable,
            Authentication authentication
            ) {
        return ResponseEntity.ok(numericFieldService.findAll(pageable, ((Employee)authentication.getPrincipal()).getManagementEntity()));
    }

    @GetMapping("/by-product/{product}")
    public ResponseEntity<List<NumericFieldResponseDTO>> getByProduct(@PathVariable UUID product) {
        return ResponseEntity.ok(numericFieldService.findByProduct(product));
    }

    @GetMapping("/search")
    public ResponseEntity<List<NumericFieldResponseDTO>> searchByLabel(@RequestParam String label) {
        return ResponseEntity.ok(numericFieldService.searchByLabel(label));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NumericFieldResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody NumericFieldRequestDTO selectFieldRequestDTO) {
        return ResponseEntity.ok(numericFieldService.updateByUuid(id, selectFieldRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        numericFieldService.deleteByUuid(id);
        return ResponseEntity.ok().build();
    }
}
