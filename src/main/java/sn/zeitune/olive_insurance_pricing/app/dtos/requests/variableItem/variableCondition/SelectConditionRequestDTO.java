package sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import sn.zeitune.olive_insurance_pricing.enums.SelectFieldOperator;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectConditionRequestDTO extends ConditionRequestDTO {
    @NotNull(message = "Operator is required")
    SelectFieldOperator operator;

    @NotNull(message = "Value is required")
    UUID value;
}
