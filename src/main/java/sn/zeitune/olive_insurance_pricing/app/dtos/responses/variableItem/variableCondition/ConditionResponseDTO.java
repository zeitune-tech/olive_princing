package sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class ConditionResponseDTO {
    UUID id;
}
