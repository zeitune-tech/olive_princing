package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Field;

public class FieldMapper {

    public static Field map(FieldRequestDTO dto, Field field) {
        field.setLabel(dto.label());
        field.setDescription(dto.description());
        field.setVariableName(dto.variableName());
        field.setType(dto.type());
        field.setToReturn(dto.toReturn());
        field.setManagementEntity(dto.managementEntity());
        field.setProduct(dto.product());
        field.setCoverage(dto.coverage());
        return field;
    }

    public static Field map(FieldRequestDTO dto) {
        return map(dto, new Field());
    }

    public static FieldResponseDTO map(Field field) {
        return FieldResponseDTO.builder()
                .id(field.getUuid())
                .label(field.getLabel())
                .description(field.getDescription())
                .variableName(field.getVariableName())
                .type(field.getType())
                .toReturn(field.getToReturn())
                .managementEntity(field.getManagementEntity())
                .product(field.getProduct())
                .coverage(field.getCoverage())
                .value(field.getValue() != null ? FieldValueMapper.map(field.getValue()) : null)
                .build();
    }
}
