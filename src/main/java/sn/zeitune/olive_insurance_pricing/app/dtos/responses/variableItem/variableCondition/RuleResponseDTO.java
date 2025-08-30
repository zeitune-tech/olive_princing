package sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RuleResponseDTO {
    UUID id;
    String label;
    String name;
    Double value;
    Set<ConditionResponseDTO> conditions;
}
