package sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.VariableItemResponseDTO;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "DTO de réponse pour les conditions de variables")
public class VariableConditionResponseDTO extends VariableItemResponseDTO {
    @Schema(description = "Règles associées")
    Set<RuleResponseDTO> rules;
    UUID coverage;
}
