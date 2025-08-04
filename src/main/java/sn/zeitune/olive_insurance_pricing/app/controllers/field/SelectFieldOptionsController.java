package sn.zeitune.olive_insurance_pricing.app.controllers.field;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldOptionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldOptionService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/app/select-field-options")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Select field options", description = "Operations pour la gestion des valeurs de champs")
public class SelectFieldOptionsController {

    private final SelectFieldOptionService selectFieldOptionService;

    @PostMapping
    @Operation(summary = "Créer une nouvelle valeur de champ", description = "Crée une nouvelle valeur de champ avec les données fournies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Valeur de champ créée avec succès",
                    content = @Content(schema = @Schema(implementation = SelectFieldOptionResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Valeur de champ déjà existante",
                    content = @Content)
    })
    public ResponseEntity<SelectFieldOptionResponseDTO> create(@Valid @RequestBody SelectFieldOptionRequestDTO selectFieldOptionRequestDTO) {
        log.info("REST request to create field value: {}", selectFieldOptionRequestDTO.getName());
        return ResponseEntity.ok(selectFieldOptionService.create(selectFieldOptionRequestDTO));
    }

    @GetMapping
    @Operation(summary = "Récupérer toutes les valeurs de champs", description = "Récupère toutes les valeurs de champs avec pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des valeurs de champs récupérée avec succès",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    public ResponseEntity<Page<SelectFieldOptionResponseDTO>> getAll(@PageableDefault(size = 20) Pageable pageable) {
        log.info("REST request to get all field values with pagination");
        return ResponseEntity.ok(selectFieldOptionService.findAll(pageable));
    }

    @GetMapping("/by-name/{name}")
    @Operation(summary = "Récupérer une valeur de champ par nom", description = "Récupère une valeur de champ spécifique par son nom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valeur de champ trouvée",
                    content = @Content(schema = @Schema(implementation = SelectFieldResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Valeur de champ non trouvée",
                    content = @Content)
    })
    public ResponseEntity<Optional<SelectFieldOptionResponseDTO>> getByName(@Parameter(description = "Nom de la valeur de champ") @PathVariable String name) {
        log.info("REST request to get field value by name: {}", name);
        return ResponseEntity.ok(selectFieldOptionService.findByName(name));
    }

    @GetMapping("/search")
    @Operation(summary = "Rechercher des valeurs de champs par nom", description = "Recherche des valeurs de champs contenant le nom spécifié")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des valeurs de champs trouvées",
                    content = @Content(schema = @Schema(implementation = List.class)))
    })
    public ResponseEntity<List<SelectFieldOptionResponseDTO>> searchByName(@Parameter(description = "Nom à rechercher") @RequestParam String name) {
        log.info("REST request to search field values by name: {}", name);
        return ResponseEntity.ok(selectFieldOptionService.searchByName(name));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une valeur de champ", description = "Met à jour une valeur de champ existante avec les nouvelles données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valeur de champ mise à jour avec succès",
                    content = @Content(schema = @Schema(implementation = SelectFieldResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Valeur de champ non trouvée",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Données invalides",
                    content = @Content)
    })
    public ResponseEntity<SelectFieldOptionResponseDTO> update(@Parameter(description = "ID de la valeur de champ") @PathVariable UUID id, @Valid @RequestBody SelectFieldOptionRequestDTO selectFieldOptionRequestDTO) {
        log.info("REST request to update field value with ID: {}", id);
        return ResponseEntity.ok(selectFieldOptionService.updateByUuid(id, selectFieldOptionRequestDTO));
    }
}
