package sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import sn.zeitune.olive_insurance_pricing.enums.NumericOperator;

import java.util.UUID;

@Builder
public record NumericalConditionRequestDTO(
        @NotNull(message = "Value is required")
        Double value,

        @NotNull(message = "Field UUID is required")
        UUID fieldId,

        @NotNull(message = "Operator is required")
        NumericOperator numericOperator
) {}
