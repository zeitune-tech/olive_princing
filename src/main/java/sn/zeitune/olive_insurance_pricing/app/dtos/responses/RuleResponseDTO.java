package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record RuleResponseDTO(
        UUID id,
        String label,
        String description,
        String variableName,
        Double value,
        Boolean toReturn,
        String managementEntity,
        UUID product,
        UUID coverage,
        Set<ConditionResponseDTO> conditions
) {}
