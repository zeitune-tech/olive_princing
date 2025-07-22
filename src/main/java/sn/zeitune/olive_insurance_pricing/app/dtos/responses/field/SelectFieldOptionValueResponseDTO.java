package sn.zeitune.olive_insurance_pricing.app.dtos.responses.field;

import lombok.Builder;

import java.util.UUID;

@Builder
public record SelectFieldOptionValueResponseDTO(
        UUID id,
        String label,
        String name,
        String group
) {
}
