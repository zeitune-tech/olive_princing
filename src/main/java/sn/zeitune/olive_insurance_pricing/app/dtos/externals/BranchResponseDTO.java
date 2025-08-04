package sn.zeitune.olive_insurance_pricing.app.dtos.externals;

import java.util.UUID;

public record BranchResponseDTO(
        UUID id,
        String name,
        String description
) {}
