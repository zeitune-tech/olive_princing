package sn.zeitune.olive_insurance_pricing.app.dtos.externals;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ProductExternalDTO(
        UUID id,
        String name,
        BranchExternalDTO branch,
        String description,
        UUID ownerId,
        String ownerName,
        Integer minRisk,
        Integer maxRisk,
        Integer minimumGuaranteeNumber,
        Boolean fleet,
        boolean hasReduction,
        String visibility,
        List<UUID> sharedWith
) {}
