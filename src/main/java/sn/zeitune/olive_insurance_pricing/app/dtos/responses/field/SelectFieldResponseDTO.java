package sn.zeitune.olive_insurance_pricing.app.dtos.responses.field;

import lombok.Builder;

import java.util.UUID;

@Builder
public record SelectFieldResponseDTO(
        UUID id,
        String label,
        String description,
        String variableName,
        Boolean toReturn,
        UUID managementEntity,
        UUID product,
        UUID coverage,
        SelectFieldOptionsResponseDTO options,
        SelectFieldOptionValueResponseDTO value
) {}
