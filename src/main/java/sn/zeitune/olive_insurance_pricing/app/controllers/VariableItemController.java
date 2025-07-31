package sn.zeitune.olive_insurance_pricing.app.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.VariableItemService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/variable-items")
@RequiredArgsConstructor
@Slf4j
public class VariableItemController {

    private final VariableItemService variableItemService;

//    @PostMapping
//    public ResponseEntity<VariableItemResponseDTO> create(@Valid @RequestBody VariableItemRequestDTO variableItemRequestDTO) {
//        return ResponseEntity.ok(variableItemService.create(variableItemRequestDTO));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<VariableItemResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(variableItemService.findByUuid(id));
    }

    @GetMapping
    public ResponseEntity<Page<VariableItemResponseDTO>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(variableItemService.findAll(pageable));
    }

    @GetMapping("/by-product/{product}")
    public ResponseEntity<List<VariableItemResponseDTO>> getByProduct(@PathVariable UUID product) {
        return ResponseEntity.ok(variableItemService.findByProduct(product));
    }

    @GetMapping("/search")
    public ResponseEntity<List<VariableItemResponseDTO>> searchByLabel(@RequestParam String label) {
        return ResponseEntity.ok(variableItemService.searchByLabel(label));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<VariableItemResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody VariableItemRequestDTO variableItemRequestDTO) {
//        return ResponseEntity.ok(variableItemService.updateByUuid(id, variableItemRequestDTO));
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable UUID id) {
//        variableItemService.deleteByUuid(id);
//        return ResponseEntity.ok().build();
//    }
}
