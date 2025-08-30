package sn.zeitune.olive_insurance_pricing.app.dtos.externals;

import lombok.Builder;

@Builder
public record AttributOptionExternalDTO(
        String valeur,
        String libelle,
        String description,
        Boolean actif
) {}