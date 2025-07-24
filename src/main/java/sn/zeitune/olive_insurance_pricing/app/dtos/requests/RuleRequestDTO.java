package sn.zeitune.olive_insurance_pricing.app.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.NumericalConditionRequestDTO;

import java.util.Set;
import java.util.UUID;

@Builder
public record RuleRequestDTO(
        @NotNull(message = "Label is required")
        @NotBlank(message = "Label must not be blank")
        String label,

        @NotNull(message = "Name is required")
        @NotBlank(message = "Name must not be blank")
        String name,

        @NotNull(message = "Value is required")
        Double value,

        Set<UUID> conditions
) {}
