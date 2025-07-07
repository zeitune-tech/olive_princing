package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.Builder;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.UUID;

@Builder
public record FieldResponseDTO(
        UUID id,
        String label,
        String description,
        String variableName,
        FieldType type,
        Boolean toReturn,
        String managementEntity,
        UUID product,
        UUID coverage,
        FieldValueResponseDTO value
) {}
