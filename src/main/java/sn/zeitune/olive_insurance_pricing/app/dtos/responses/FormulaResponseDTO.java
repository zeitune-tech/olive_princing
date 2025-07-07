package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.Builder;

import java.util.UUID;

@Builder
public record FormulaResponseDTO(
        UUID id,
        String label,
        String description,
        String variableName,
        String expression,
        Boolean toReturn,
        String managementEntity,
        UUID product,
        UUID coverage
) {}
