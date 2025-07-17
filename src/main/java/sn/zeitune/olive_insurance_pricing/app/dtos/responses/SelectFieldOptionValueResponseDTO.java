package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.Builder;

import java.util.UUID;

@Builder
public record FieldPossibilitiesValueResponseDTO(
        UUID id,
        String label,
        String name,
        String group
) {
}
