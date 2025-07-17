package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
@Schema(description = "DTO de réponse pour les conditions de variables")
public record VariableConditionResponseDTO(

        @Schema(description = "UUID unique", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID uuid,
        
        @Schema(description = "Libellé de la condition de variable", example = "Condition âge")
        String label,
        
        @Schema(description = "Description de la condition de variable", example = "Condition pour vérifier l'âge du conducteur")
        String description,
        
        @Schema(description = "Nom de la variable", example = "age_conducteur")
        String variableName,
        
        @Schema(description = "Indique si la variable doit être retournée", example = "true")
        Boolean toReturn,
        
        @Schema(description = "Entité de gestion", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID managementEntity,
        
        @Schema(description = "Produit associé", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID product,
        
        @Schema(description = "Couverture associée", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID coverage,
        
        @Schema(description = "Règles associées")
        Set<RuleResponseDTO> rules,
        
        @Schema(description = "Date de création")
        LocalDateTime createdAt,
        
        @Schema(description = "Date de dernière modification")
        LocalDateTime updatedAt
) {
}
