package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;

public class ConstantMapper {

    public static Constant map(ConstantRequestDTO dto, Constant constant) {
        constant.setLabel(dto.label());
        constant.setDescription(dto.description());
        constant.setVariableName(dto.variableName());
        constant.setValue(dto.value());
        constant.setToReturn(dto.toReturn());
        constant.setManagementEntity(dto.managementEntity());
        constant.setProduct(dto.product());
        constant.setCoverage(dto.coverage());
        return constant;
    }

    public static Constant map(ConstantRequestDTO requestDTO) {
        return map(requestDTO, new Constant());
    }

    public static ConstantResponseDTO map(Constant constant) {
        return ConstantResponseDTO.builder()
                .id(constant.getUuid())
                .label(constant.getLabel())
                .description(constant.getDescription())
                .variableName(constant.getVariableName())
                .value(constant.getValue())
                .toReturn(constant.getToReturn())
                .managementEntity(constant.getManagementEntity())
                .product(constant.getProduct())
                .coverage(constant.getCoverage())
                .build();
    }
}
