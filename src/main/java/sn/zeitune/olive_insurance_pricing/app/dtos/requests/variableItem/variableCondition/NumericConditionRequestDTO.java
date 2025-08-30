package sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import sn.zeitune.olive_insurance_pricing.enums.NumericOperator;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NumericConditionRequestDTO extends ConditionRequestDTO {
    @NotNull(message = "Operator is required")
    NumericOperator numericOperator;

    double value;
    double minValue;
    double maxValue;
}
