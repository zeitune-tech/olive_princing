package sn.zeitune.olive_insurance_pricing.app.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import sn.zeitune.olive_insurance_pricing.enums.Operator;

import java.util.UUID;

@Builder
public record ConditionRequestDTO(
        @NotNull(message = "Value is required")
        Double value,

        @NotNull(message = "Field UUID is required")
        UUID fieldUuid,

        @NotNull(message = "Operator is required")
        Operator operator
) {}
