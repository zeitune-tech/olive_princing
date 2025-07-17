package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.SelectFieldOptionValueRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.SelectFieldOptionValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.SelectFieldOptionValue;

public class SelectFieldOptionValueMapper {

    public static SelectFieldOptionValue map(SelectFieldOptionValueRequestDTO dto, SelectFieldOptionValue field) {
        field.setLabel(dto.label());
        field.setName(dto.name());
        field.setGroup(dto.group());
        return field;
    }

    public static SelectFieldOptionValue map(SelectFieldOptionValueRequestDTO dto) {
        return map(dto, new SelectFieldOptionValue());
    }

    public static SelectFieldOptionValueResponseDTO map(SelectFieldOptionValue field) {
        return SelectFieldOptionValueResponseDTO.builder()
                .id(field.getUuid())
                .label(field.getLabel())
                .name(field.getName())
                .group(field.getGroup())
                .build();
    }
}
