package sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import sn.zeitune.olive_insurance_pricing.enums.NumericOperator;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NumericConditionRequestDTO extends ConditionRequestDTO {
    @NotNull(message = "Operator is required")
    NumericOperator numericOperator;

    @NotNull(message = "Value is required")
    double value;
}
