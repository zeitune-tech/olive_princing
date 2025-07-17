package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.SelectFieldOptionsRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.SelectFieldOptionsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.SelectFieldOptions;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SelectFieldOptionsMapper {

    // Backward compatibility
    public static SelectFieldOptions map(SelectFieldOptionsRequestDTO requestDTO, SelectFieldOptions selectFieldOptions) {
        selectFieldOptions.setLabel(requestDTO.label());
        selectFieldOptions.setName(requestDTO.name());
        selectFieldOptions.setDescription(requestDTO.description());
        return selectFieldOptions;
    }

    public static SelectFieldOptions map(SelectFieldOptionsRequestDTO requestDTO) {
        return map(requestDTO, new SelectFieldOptions());
    }

    public static SelectFieldOptionsResponseDTO map(SelectFieldOptions selectFieldOptions) {
        return SelectFieldOptionsResponseDTO.builder()
                .id(selectFieldOptions.getUuid())
                .label(selectFieldOptions.getLabel())
                .name(selectFieldOptions.getName())
                .description(selectFieldOptions.getDescription())
                .possibilities( selectFieldOptions.getPossibilities() == null ? new ArrayList<>() : selectFieldOptions.getPossibilities().stream().map(SelectFieldOptionValueMapper::map).collect(Collectors.toList()) )
                .build();
    }
}
