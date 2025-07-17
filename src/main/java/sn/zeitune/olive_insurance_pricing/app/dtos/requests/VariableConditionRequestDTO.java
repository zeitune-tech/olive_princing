package sn.zeitune.olive_insurance_pricing.app.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

@Schema(description = "DTO de requête pour les conditions de variables")
public record VariableConditionRequestDTO(
        
        @Schema(description = "Libellé de la condition de variable", example = "Condition âge")
        @NotBlank(message = "Le libellé est obligatoire")
        String label,
        
        @Schema(description = "Description de la condition de variable", example = "Condition pour vérifier l'âge du conducteur")
        String description,
        
        @Schema(description = "Nom de la variable", example = "age_conducteur")
        @NotBlank(message = "Le nom de la variable est obligatoire")
        String variableName,
        
        @Schema(description = "Indique si la variable doit être retournée", example = "true")
        Boolean toReturn,
        
        @Schema(description = "Entité de gestion", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID managementEntity,
        
        @Schema(description = "Produit associé", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID product,
        
        @Schema(description = "Couverture associée", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID coverage,
        
        @Schema(description = "IDs des règles associées")
        Set<UUID> ruleIds
) {
}
