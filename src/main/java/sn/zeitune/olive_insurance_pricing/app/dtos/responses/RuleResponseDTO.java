package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.Builder;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.NumericalConditionResponseDTO;

import java.util.Set;
import java.util.UUID;

@Builder
public record RuleResponseDTO (
        UUID id,
        String label,
        String description,
        String variableName,
        Double value,
        Boolean toReturn,
        UUID managementEntity,
        UUID product,
        UUID coverage,
        Set<Object> conditions
) {}
