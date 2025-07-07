package sn.zeitune.olive_insurance_pricing.app.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FieldValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.FieldValueService;

@RestController
@RequestMapping("/app/field-values")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Field Values", description = "Gestion des valeurs de champs")
public class FieldValueControllerSimple {

    private final FieldValueService fieldValueService;

    @GetMapping
    @Operation(summary = "Récupérer toutes les valeurs de champs")
    public ResponseEntity<Page<FieldValueResponseDTO>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        log.info("REST request to get all field values with pagination");
        return null;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une valeur de champ par ID")
    public ResponseEntity<FieldValueResponseDTO> getById(@PathVariable Long id) {
        log.info("REST request to get field value by ID: {}", id);
        return null;
    }
}
