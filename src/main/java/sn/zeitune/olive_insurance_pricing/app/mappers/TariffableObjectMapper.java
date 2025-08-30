package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.externals.TariffableAttributExternalDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.TariffableAttributResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.TariffableObjectResponseDTO;

import java.util.HashSet;
import java.util.Set;

public class TariffableObjectMapper {

    public static TariffableObjectResponseDTO map (TariffableAttributExternalDTO tariffableAttributExternalDTO) {
        Set<TariffableAttributResponseDTO> tariffableAttributs = new HashSet<>();
        tariffableAttributs.add(TariffableAttributMapper.map(tariffableAttributExternalDTO));
        return TariffableObjectResponseDTO.builder()
                .name(tariffableAttributExternalDTO.entite())
                .attributs(tariffableAttributs)
                .build();
    }
}
