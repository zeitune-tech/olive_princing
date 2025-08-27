package sn.zeitune.olive_insurance_pricing.app.mappers.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldOptionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOption;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SelectFieldOptionMapper {

    // Backward compatibility
    public static SelectFieldOption map(SelectFieldOptionRequestDTO requestDTO, SelectFieldOption selectFieldOption) {
        selectFieldOption.setLabel(requestDTO.getLabel());
        selectFieldOption.setName(requestDTO.getName());
        selectFieldOption.setDescription(requestDTO.getDescription());
        return selectFieldOption;
    }

    public static SelectFieldOption map(SelectFieldOptionRequestDTO requestDTO) {
        return map(requestDTO, new SelectFieldOption());
    }

    public static SelectFieldOptionResponseDTO map(SelectFieldOption selectFieldOption) {
        return SelectFieldOptionResponseDTO.builder()
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
