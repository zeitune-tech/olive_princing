package sn.zeitune.olive_insurance_pricing.app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.PricingTypeRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.PricingTypeResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.PricingTypeService;
import sn.zeitune.olive_insurance_pricing.security.Employee;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/pricing-types")
@RequiredArgsConstructor
public class PricingTypeController {

    private final PricingTypeService pricingTypeService;

    @PostMapping
    public ResponseEntity<PricingTypeResponseDTO> create(
            @Valid @RequestBody PricingTypeRequestDTO request,
            Authentication authentication
    ) {
        return new ResponseEntity<>(pricingTypeService.create(
                request,
                ((Employee) authentication.getPrincipal()).getManagementEntity()
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PricingTypeResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody PricingTypeRequestDTO request,
            Authentication authentication
    ) {
        return ResponseEntity.ok(pricingTypeService.update(
                id,
                request,
                ((Employee) authentication.getPrincipal()).getManagementEntity()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        pricingTypeService.delete(id, ((Employee) authentication.getPrincipal()).getManagementEntity());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PricingTypeResponseDTO> getById(
            @PathVariable UUID id,
            @RequestParam(required = false, defaultValue = "false") boolean withDetailed,
            Authentication authentication
    ) {
        if (withDetailed) {
            return ResponseEntity.ok(pricingTypeService.getDetailedById(
                    id,
                    ((Employee) authentication.getPrincipal()).getManagementEntity())
            );
        }
        return ResponseEntity.ok(pricingTypeService.getById(
                id,
                ((Employee) authentication.getPrincipal()).getManagementEntity())
        );
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<PricingTypeResponseDTO>> getByProduct(
            @PathVariable UUID productId,
            Authentication authentication
    ) {
        return ResponseEntity.ok(pricingTypeService.getByProduct(
                productId,
                ((Employee) authentication.getPrincipal()).getManagementEntity()
        ));
    }

    @GetMapping
    public ResponseEntity<Page<PricingTypeResponseDTO>> getAll(
            @PageableDefault(size = 20) Pageable pageable,
            Authentication authentication
    ) {
        return ResponseEntity.ok(pricingTypeService.getAllActive(
                pageable,
                ((Employee) authentication.getPrincipal()).getManagementEntity())
        );
    }
}
