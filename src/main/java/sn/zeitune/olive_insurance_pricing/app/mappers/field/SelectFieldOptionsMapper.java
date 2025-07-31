package sn.zeitune.olive_insurance_pricing.app.mappers.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldOptionsRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOption;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SelectFieldOptionsMapper {

    // Backward compatibility
    public static SelectFieldOption map(SelectFieldOptionsRequestDTO requestDTO, SelectFieldOption selectFieldOption) {
        selectFieldOption.setLabel(requestDTO.label());
        selectFieldOption.setName(requestDTO.name());
        selectFieldOption.setDescription(requestDTO.description());
        return selectFieldOption;
    }

    public static SelectFieldOption map(SelectFieldOptionsRequestDTO requestDTO) {
        return map(requestDTO, new SelectFieldOption());
    }

    public static SelectFieldOptionsResponseDTO map(SelectFieldOption selectFieldOption) {
        return SelectFieldOptionsResponseDTO.builder()
                .id(selectFieldOption.getUuid())
                .label(selectFieldOption.getLabel())
                .name(selectFieldOption.getName())
                .description(selectFieldOption.getDescription())
                .possibilities(
                        selectFieldOption.getPossibilities() == null ?
                                new ArrayList<>() :
                                selectFieldOption.getPossibilities()
                                        .stream().map(SelectFieldOptionValueMapper::map)
                                        .collect(Collectors.toList())
                )
                .build();
    }
}
