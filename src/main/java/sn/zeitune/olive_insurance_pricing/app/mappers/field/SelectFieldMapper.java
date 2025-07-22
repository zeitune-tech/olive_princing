package sn.zeitune.olive_insurance_pricing.app.mappers.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;

public class SelectFieldMapper {

    public static SelectField map(SelectFieldRequestDTO dto, SelectField field) {
        field.setLabel(dto.label());
        field.setDescription(dto.description());
        field.setVariableName(dto.variableName());
        field.setToReturn(dto.toReturn());
        field.setManagementEntity(dto.managementEntity());
        field.setProduct(dto.product());
        field.setCoverage(dto.coverage());
        return field;
    }

    public static SelectField map(SelectFieldRequestDTO dto) {
        return map(dto, new SelectField());
    }

    public static SelectFieldResponseDTO map(SelectField field) {
        // TODO
        return SelectFieldResponseDTO.builder()
                .id(field.getUuid())
                .label(field.getLabel())
                .description(field.getDescription())
                .variableName(field.getVariableName())
                .toReturn(field.getToReturn())
                .managementEntity(field.getManagementEntity())
                .product(field.getProduct())
                .coverage(field.getCoverage())
                .options(SelectFieldOptionsMapper.map(field.getOptions()))
                .value(field.getValue() == null ? null : SelectFieldOptionValueMapper.map(field.getValue()))
                .build();
    }
}
