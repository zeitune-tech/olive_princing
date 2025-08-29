package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.externals.AttributOptionExternalDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.externals.TariffableAttributExternalDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.TariffableAttributResponseDTO;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TariffableAttributMapper {

    public static TariffableAttributResponseDTO map(TariffableAttributExternalDTO tariffableAttributExternalDTO) {
        List<AttributOptionExternalDTO> options = Arrays.asList(
                AttributOptionExternalDTO.builder()
                        .actif(true)
                        .description("Description #1")
                        .libelle("Libellé #1")
                        .valeur("Valeur #1")
                        .build(),
                AttributOptionExternalDTO.builder()
                        .actif(true)
                        .description("Description #2")
                        .libelle("Libellé #2")
                        .valeur("Valeur #2")
                        .build(),
                AttributOptionExternalDTO.builder()
                        .actif(true)
                        .description("Description #3")
                        .libelle("Libellé #3")
                        .valeur("Valeur #3")
                        .build()
        );

        if (FieldType.fromString(tariffableAttributExternalDTO.type().type()) == FieldType.SELECT)
            return TariffableAttributResponseDTO.builder()
                    .controlName(tariffableAttributExternalDTO.nom())
                    .label(tariffableAttributExternalDTO.libelle())
                    .type(FieldType.fromString(tariffableAttributExternalDTO.type().type()))
                    .options(
                            tariffableAttributExternalDTO.type().options() != null ? tariffableAttributExternalDTO.type().options().stream().map(AttributOptionMapper::map).collect(Collectors.toList()) :
                            options.stream().map(AttributOptionMapper::map).collect(Collectors.toList())
                    )
                    .build();

        return TariffableAttributResponseDTO.builder()
                .controlName(tariffableAttributExternalDTO.nom())
                .label(tariffableAttributExternalDTO.libelle())
                .type(FieldType.fromString(tariffableAttributExternalDTO.type().type()))
                .options(tariffableAttributExternalDTO.type().options() == null ? null :
                        tariffableAttributExternalDTO.type().options().stream()
                                .map(AttributOptionMapper::map)
                                .toList()
                )
                .build();

    }
}
