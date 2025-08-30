package sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VariableItemResponseDTO {
    @Schema(description = "UUID unique", example = "123e4567-e89b-12d3-a456-426614174000")
    UUID id;
    @Schema(description = "Libellé de la condition de variable", example = "Condition âge")
    String label;
    @Schema(description = "Description de la condition de variable", example = "Condition pour vérifier l'âge du conducteur")
    String description;
    @Schema(description = "Nom de la variable", example = "age_conducteur")
    String variableName;
    @Schema(description = "Type de variable", example = "SELECT_FIELD")
    TypeOfVariable type;
    @Schema(description = "Indique si la variable doit être retournée", example = "true")
    Boolean toReturn;
    @Schema(description = "Branch associée", example = "123e4567-e89b-12d3-a456-426614174000")
    UUID branch;
    @Schema(description = "Produit associé", example = "123e4567-e89b-12d3-a456-426614174000")
    UUID product;
    @Schema(description = "Type de tarification", example = "123e4567-e89b-12d3-a456-426614174000")
    UUID pricingType;
    @Schema(description = "Couverture associée", example = "123e4567-e89b-12d3-a456-426614174000")
    UUID coverage;
    @Schema(description = "Date de création", example = "2023-10-01T12:00:00Z")
    Instant createdAt;
    @Schema(description = "Date de mise à jour", example = "2023-10-01T12:00:00Z")
    Instant updatedAt;
    LocalDate dateEffective;
}
