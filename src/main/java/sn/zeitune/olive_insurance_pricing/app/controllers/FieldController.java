package sn.zeitune.olive_insurance_pricing.app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.FieldDto;
import sn.zeitune.olive_insurance_pricing.app.services.FieldService;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/fields")
@RequiredArgsConstructor
@Slf4j
public class FieldController {

    private final FieldService fieldService;

    @PostMapping
    public ResponseEntity<FieldDto> create(@Valid @RequestBody FieldDto fieldDto) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldDto> getById(@PathVariable Long id) {
        return null;
    }

    @GetMapping
    public ResponseEntity<Page<FieldDto>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return null;
    }

    @GetMapping("/by-type/{type}")
    public ResponseEntity<List<FieldDto>> getByType(@PathVariable FieldType type) {
        return null;
    }

    @GetMapping("/by-product/{product}")
    public ResponseEntity<List<FieldDto>> getByProduct(@PathVariable UUID product) {
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<List<FieldDto>> searchByLabel(@RequestParam String label) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldDto> update(@PathVariable Long id, @Valid @RequestBody FieldDto fieldDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return null;
    }
}
