package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.SelectFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Field;
import sn.zeitune.olive_insurance_pricing.app.entities.SelectField;

public class SelectedFieldMapper {

    public static SelectField map(SelectFieldRequestDTO dto, SelectField field) {
        field.setLabel(dto.label());
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
                .options(FieldValueMapper.map(field.getOptions()))
                .value(FieldPossibilitesValueMapper.map(field.getValue()))
                .build();
    }
}
