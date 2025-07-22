package sn.zeitune.olive_insurance_pricing.app.dtos.responses.field;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record SelectFieldOptionsResponseDTO(
        UUID id,
        String label,
        String name,
        String description,
        List<SelectFieldOptionValueResponseDTO> possibilities
) {}
