package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.Builder;

@Builder
public record FieldValueResponseDTO(
        Long id,
        String name,
        String description,
        String value
) {}
