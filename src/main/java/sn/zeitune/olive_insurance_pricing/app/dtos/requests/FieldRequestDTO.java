package sn.zeitune.olive_insurance_pricing.app.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.UUID;

@Builder
public record FieldRequestDTO(
        @NotNull(message = "Label is required")
        @NotBlank(message = "Label must not be blank")
        String label,

        String description,

        @NotNull(message = "Variable name is required")
        @NotBlank(message = "Variable name must not be blank")
        String variableName,

        @NotNull(message = "Field type is required")
        FieldType type,

        Boolean toReturn,

        UUID managementEntity,

        UUID product,

        UUID coverage
) {}
