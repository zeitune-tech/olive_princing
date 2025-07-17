package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FieldPossibilitiesValueRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FieldPossibilitiesValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.SelectFieldOptionValue;

public class FieldPossibilitiesValueMapper {

    public static SelectFieldOptionValue map(FieldPossibilitiesValueRequestDTO dto, SelectFieldOptionValue field) {
        field.setLabel(dto.label());
        field.setName(dto.name());
        field.setGroup(dto.group());
        return field;
    }

    public static SelectFieldOptionValue map(FieldPossibilitiesValueRequestDTO dto) {
        return map(dto, new SelectFieldOptionValue());
    }

    public static FieldPossibilitiesValueResponseDTO map(SelectFieldOptionValue field) {
        return FieldPossibilitiesValueResponseDTO.builder()
                .id(field.getUuid())
                .label(field.getLabel())
                .name(field.getName())
                .group(field.getGroup())
                .build();
    }
}
