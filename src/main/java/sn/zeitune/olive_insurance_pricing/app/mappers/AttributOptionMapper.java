package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.externals.AttributOptionExternalDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.AttributOptionResponseDTO;

public class AttributOptionMapper {
    public static AttributOptionResponseDTO map (AttributOptionExternalDTO attributOptionExternalDTO) {
        if (attributOptionExternalDTO == null)
            return null;

        return AttributOptionResponseDTO.builder()
                .value(attributOptionExternalDTO.valeur())
                .label(attributOptionExternalDTO.libelle())
                .isActive(attributOptionExternalDTO.actif())
                .build();
    }
}
