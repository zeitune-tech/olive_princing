package sn.zeitune.olive_insurance_pricing.app.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "DTO de requête pour les conditions de variables")
public class VariableConditionRequestDTO extends VariableItemRequestDTO {
    @Schema(description = "IDs des règles associées")
    Set<UUID> ruleIds = new HashSet<>();
    @NotNull(message = "Coverage is required")
    @NotBlank(message = "Coverage must not be blank")
    UUID coverage;
    @NotNull(message = "Pricing type is required")
    @NotBlank(message = "Pricing type must not be blank")
    UUID pricingType;
}
