package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.NumericFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.SelectFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Field;
import sn.zeitune.olive_insurance_pricing.app.entities.NumericField;

public class NumericFieldMapper {

    public static NumericField map(NumericFieldRequestDTO dto, NumericField field) {
        field.setLabel(dto.label());
        field.setDescription(dto.description());
        field.setVariableName(dto.variableName());
        field.setToReturn(dto.toReturn());
        field.setManagementEntity(dto.managementEntity());
        field.setProduct(dto.product());
        field.setCoverage(dto.coverage());
        return field;
    }

    public static NumericField map(NumericFieldRequestDTO dto) {
        return map(dto, new NumericField());
    }

    public static NumericFieldResponseDTO map(NumericField field) {
        return NumericFieldResponseDTO.builder()
                .id(field.getUuid())
                .label(field.getLabel())
                .description(field.getDescription())
                .variableName(field.getVariableName())
                .toReturn(field.getToReturn())
                .managementEntity(field.getManagementEntity())
                .product(field.getProduct())
                .coverage(field.getCoverage())
                .value(field.getValue())
                .build();
    }
}
