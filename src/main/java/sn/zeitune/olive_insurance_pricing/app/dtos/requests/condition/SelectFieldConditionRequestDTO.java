package sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import sn.zeitune.olive_insurance_pricing.enums.SelectFieldOperator;

import java.util.UUID;

@Builder
public record SelectFieldConditionRequestDTO(
        @NotNull(message = "Value is required")
        UUID value,

        @NotNull(message = "Field UUID is required")
        UUID fieldId,

        @NotNull(message = "Operator is required")
        SelectFieldOperator operator
) {}
