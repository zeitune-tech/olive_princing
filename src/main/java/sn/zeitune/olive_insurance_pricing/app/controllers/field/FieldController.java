package sn.zeitune.olive_insurance_pricing.app.controllers.field;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.FieldService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/fields")
@RequiredArgsConstructor
@Slf4j
public class FieldController {

    private final FieldService fieldService;

//    @PostMapping
//    public ResponseEntity<FieldResponseDTO> create(@Valid @RequestBody FieldRequestDTO fieldRequestDTO) {
//        return ResponseEntity.ok(fieldService.create(fieldRequestDTO));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(fieldService.findByUuid(id));
    }

    @GetMapping
    public ResponseEntity<Page<FieldResponseDTO>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(fieldService.findAll(pageable));
    }

    @GetMapping("/by-product/{product}")
    public ResponseEntity<List<FieldResponseDTO>> getByProduct(@PathVariable UUID product) {
        return ResponseEntity.ok(fieldService.findByProduct(product));
    }

    @GetMapping("/search")
    public ResponseEntity<List<FieldResponseDTO>> searchByLabel(@RequestParam String label) {
        return ResponseEntity.ok(fieldService.searchByLabel(label));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<FieldResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody FieldRequestDTO fieldRequestDTO) {
//        return ResponseEntity.ok(fieldService.updateByUuid(id, fieldRequestDTO));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        fieldService.deleteByUuid(id);
        return ResponseEntity.ok().build();
    }
}
