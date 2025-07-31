package sn.zeitune.olive_insurance_pricing.app.mappers.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldOptionValueRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;

public class SelectFieldOptionValueMapper {

    public static SelectFieldOptionValue map(SelectFieldOptionValueRequestDTO dto, SelectFieldOptionValue field) {
        field.setLabel(dto.getLabel());
        field.setName(dto.getName());
        field.setGroup(dto.getGroup());
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
