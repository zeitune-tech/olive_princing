package sn.zeitune.olive_insurance_pricing.app.dtos.requests;

import java.util.UUID;

public record FieldPossibilitiesValueRequestDTO(
        String label,
        String name,
        String group
) {
}
