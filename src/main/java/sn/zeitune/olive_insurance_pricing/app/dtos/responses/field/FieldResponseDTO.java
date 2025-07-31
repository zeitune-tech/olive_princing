package sn.zeitune.olive_insurance_pricing.app.dtos.responses.field;

import lombok.Builder;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.UUID;

@Builder
public record FieldResponseDTO(
        UUID id,
        String label,
        String description,
        String variableName,
        Boolean toReturn,
        UUID managementEntity,
        UUID product,
        UUID coverage,

        FieldType type,
        SelectFieldOptionsResponseDTO options,
        SelectFieldOptionValueResponseDTO value
) {}
