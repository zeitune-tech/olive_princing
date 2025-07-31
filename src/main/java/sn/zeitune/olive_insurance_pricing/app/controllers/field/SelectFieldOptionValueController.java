package sn.zeitune.olive_insurance_pricing.app.controllers.field;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldOptionValueRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldOptionValueService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/select-field-option-values")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Select field option value", description = "Operations pour la gestion des valeurs de possibilités de champs")
public class SelectFieldOptionValueController {

    private final SelectFieldOptionValueService selectFieldOptionValueService;

    @PostMapping
    @Operation(summary = "Créer une nouvelle valeur de possibilité", description = "Crée une nouvelle valeur de possibilité de champ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valeur de possibilité créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données d'entrée invalides")
    })
    public ResponseEntity<SelectFieldOptionValueResponseDTO> create(@Valid @RequestBody SelectFieldOptionValueRequestDTO selectFieldOptionValueRequestDTO) {
        log.info("REST request to create field possibilities value: {}", selectFieldOptionValueRequestDTO.getName());
        SelectFieldOptionValueResponseDTO response = selectFieldOptionValueService.create(selectFieldOptionValueRequestDTO);
        log.info("Created field possibilities value with UUID: {}", response.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une valeur de possibilité par UUID", description = "Récupère une valeur de possibilité par son UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valeur de possibilité trouvée"),
            @ApiResponse(responseCode = "404", description = "Valeur de possibilité non trouvée")
    })
    public ResponseEntity<SelectFieldOptionValueResponseDTO> getById(@PathVariable UUID id) {
        log.info("REST request to get field possibilities value by UUID: {}", id);
        SelectFieldOptionValueResponseDTO response = selectFieldOptionValueService.findByUuid(id);
        log.info("Found field possibilities value: {}", response.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Lister toutes les valeurs de possibilités avec pagination", description = "Récupère toutes les valeurs de possibilités avec pagination")
    public ResponseEntity<Page<SelectFieldOptionValueResponseDTO>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        log.info("REST request to get all field possibilities values with pagination");
        Page<SelectFieldOptionValueResponseDTO> response = selectFieldOptionValueService.findAll(pageable);
        log.info("Found {} field possibilities values", response.getTotalElements());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-name/{name}")
    @Operation(summary = "Récupérer une valeur de possibilité par nom", description = "Récupère une valeur de possibilité par son nom")
    public ResponseEntity<SelectFieldOptionValueResponseDTO> getByName(@PathVariable String name) {
        log.info("REST request to get field possibilities value by name: {}", name);
        return selectFieldOptionValueService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-group/{group}")
    @Operation(summary = "Récupérer les valeurs de possibilités par groupe", description = "Récupère toutes les valeurs de possibilités d'un groupe donné")
    public ResponseEntity<List<SelectFieldOptionValueResponseDTO>> getByGroup(@PathVariable String group) {
        log.info("REST request to get field possibilities values by group: {}", group);
        List<SelectFieldOptionValueResponseDTO> response = selectFieldOptionValueService.findByGroup(group);
        log.info("Found {} field possibilities values for group: {}", response.size(), group);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @Operation(summary = "Rechercher des valeurs de possibilités par label", description = "Recherche des valeurs de possibilités par label (recherche partielle insensible à la casse)")
    public ResponseEntity<List<SelectFieldOptionValueResponseDTO>> searchByLabel(@RequestParam String label) {
        log.info("REST request to search field possibilities values by label: {}", label);
        List<SelectFieldOptionValueResponseDTO> response = selectFieldOptionValueService.searchByLabel(label);
        log.info("Found {} field possibilities values matching label: {}", response.size(), label);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search-name")
    @Operation(summary = "Rechercher des valeurs de possibilités par nom", description = "Recherche des valeurs de possibilités par nom (recherche partielle insensible à la casse)")
    public ResponseEntity<List<SelectFieldOptionValueResponseDTO>> searchByName(@RequestParam String name) {
        log.info("REST request to search field possibilities values by name: {}", name);
        List<SelectFieldOptionValueResponseDTO> response = selectFieldOptionValueService.searchByName(name);
        log.info("Found {} field possibilities values matching name: {}", response.size(), name);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une valeur de possibilité", description = "Met à jour une valeur de possibilité existante par son UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valeur de possibilité mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Valeur de possibilité non trouvée"),
            @ApiResponse(responseCode = "400", description = "Données d'entrée invalides")
    })
    public ResponseEntity<SelectFieldOptionValueResponseDTO> update(
            @PathVariable UUID id, 
            @Valid @RequestBody SelectFieldOptionValueRequestDTO selectFieldOptionValueRequestDTO) {
        log.info("REST request to update field possibilities value with UUID: {}, data: {}", id, selectFieldOptionValueRequestDTO);
        SelectFieldOptionValueResponseDTO response = selectFieldOptionValueService.updateByUuid(id, selectFieldOptionValueRequestDTO);
        log.info("Updated field possibilities value with UUID: {}", response.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une valeur de possibilité", description = "Supprime une valeur de possibilité par son UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valeur de possibilité supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Valeur de possibilité non trouvée")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        log.info("REST request to delete field possibilities value with UUID: {}", id);
        selectFieldOptionValueService.deleteByUuid(id);
        log.info("Deleted field possibilities value with UUID: {}", id);
        return ResponseEntity.ok().build();
    }
}