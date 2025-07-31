package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.Builder;

import java.util.UUID;

@Builder
public record VariableItemResponseDTO (
        UUID id,
        String label,
        String description,
        String variableName,
        Boolean toReturn,
        UUID managementEntity,
        UUID product,
        UUID coverage
) {

}
