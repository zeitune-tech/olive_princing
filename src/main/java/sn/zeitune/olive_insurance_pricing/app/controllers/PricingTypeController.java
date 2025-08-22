package sn.zeitune.olive_insurance_pricing.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.PricingTypeRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.PricingTypeResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.PricingTypeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/pricing-types")
@RequiredArgsConstructor
public class PricingTypeController {

    private final PricingTypeService pricingTypeService;

    @PostMapping
    public ResponseEntity<PricingTypeResponseDTO> create(@RequestBody PricingTypeRequestDTO request) {
        return new ResponseEntity<>(pricingTypeService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PricingTypeResponseDTO> update(@PathVariable UUID id, @RequestBody PricingTypeRequestDTO request) {
        return ResponseEntity.ok(pricingTypeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        pricingTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PricingTypeResponseDTO> getById(
            @PathVariable UUID id,
            @RequestParam(required = false, defaultValue = "false") boolean withVariables
            ) {
        if (withVariables) {
            return ResponseEntity.ok(pricingTypeService.getDetailedById(id));
        }
        return ResponseEntity.ok(pricingTypeService.getById(id));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<PricingTypeResponseDTO>> getByProduct(@PathVariable UUID productId) {
        return ResponseEntity.ok(pricingTypeService.getByProduct(productId));
    }

    @GetMapping
    public ResponseEntity<Page<PricingTypeResponseDTO>> getAll(
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return ResponseEntity.ok(pricingTypeService.getAll(pageable));
    }
}
