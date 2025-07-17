package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record FieldValueResponseDTO(
        UUID id,
        String label,
        String name,
        String description,
        List<FieldPossibilitiesValueResponseDTO> possibilities
) {}
