package sn.zeitune.olive_insurance_pricing.app.controllers.field;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/select-fields")
@RequiredArgsConstructor
@Slf4j
public class SelectFieldController {

    private final SelectFieldService selectFieldService;

    @PostMapping
    public ResponseEntity<SelectFieldResponseDTO> create(@Valid @RequestBody SelectFieldRequestDTO selectFieldRequestDTO) {
        return ResponseEntity.ok(selectFieldService.create(selectFieldRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SelectFieldResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(selectFieldService.findByUuid(id));
    }

    @GetMapping
    public ResponseEntity<Page<SelectFieldResponseDTO>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(selectFieldService.findAll(pageable));
    }

    @GetMapping("/by-product/{product}")
    public ResponseEntity<List<SelectFieldResponseDTO>> getByProduct(@PathVariable UUID product) {
        return ResponseEntity.ok(selectFieldService.findByProduct(product));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SelectFieldResponseDTO>> searchByLabel(@RequestParam String label) {
        return ResponseEntity.ok(selectFieldService.searchByLabel(label));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SelectFieldResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody SelectFieldRequestDTO selectFieldRequestDTO) {
        return ResponseEntity.ok(selectFieldService.updateByUuid(id, selectFieldRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        selectFieldService.deleteByUuid(id);
        return ResponseEntity.ok().build();
    }
}
