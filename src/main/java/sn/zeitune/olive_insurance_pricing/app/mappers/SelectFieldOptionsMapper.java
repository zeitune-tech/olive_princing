package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FieldValueRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FieldValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.SelectFieldOptions;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FieldValueMapper {

    // Backward compatibility
    public static SelectFieldOptions map(FieldValueRequestDTO requestDTO, SelectFieldOptions selectFieldOptions) {
        selectFieldOptions.setLabel(requestDTO.label());
        selectFieldOptions.setName(requestDTO.name());
        selectFieldOptions.setDescription(requestDTO.description());
        return selectFieldOptions;
    }

    public static SelectFieldOptions map(FieldValueRequestDTO requestDTO) {
        return map(requestDTO, new SelectFieldOptions());
    }

    public static FieldValueResponseDTO map(SelectFieldOptions selectFieldOptions) {
        return FieldValueResponseDTO.builder()
                .id(selectFieldOptions.getUuid())
                .label(selectFieldOptions.getLabel())
                .name(selectFieldOptions.getName())
                .description(selectFieldOptions.getDescription())
                .possibilities( selectFieldOptions.getPossibilities() == null ? new ArrayList<>() : selectFieldOptions.getPossibilities().stream().map(FieldPossibilitiesValueMapper::map).collect(Collectors.toList()) )
                .build();
    }
}
