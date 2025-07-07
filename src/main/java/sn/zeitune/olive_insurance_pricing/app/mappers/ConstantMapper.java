package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;

public class ConstantMapper {

    public static ConstantResponseDTO toResponseDTO(Constant constant) {
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

    public static Constant toEntity(ConstantRequestDTO requestDTO) {
        return Constant.builder()
                .label(requestDTO.label())
                .description(requestDTO.description())
                .variableName(requestDTO.variableName())
                .value(requestDTO.value())
                .toReturn(requestDTO.toReturn())
                .managementEntity(requestDTO.managementEntity())
                .product(requestDTO.product())
                .coverage(requestDTO.coverage())
                .build();
    }

    public static void updateEntityFromRequestDTO(Constant constant, ConstantRequestDTO requestDTO) {
        constant.setLabel(requestDTO.label());
        constant.setDescription(requestDTO.description());
        constant.setVariableName(requestDTO.variableName());
        constant.setValue(requestDTO.value());
        constant.setToReturn(requestDTO.toReturn());
        constant.setManagementEntity(requestDTO.managementEntity());
        constant.setProduct(requestDTO.product());
        constant.setCoverage(requestDTO.coverage());
    }

    // Backward compatibility
    public static Constant map(ConstantRequestDTO dto, Constant constant) {
        updateEntityFromRequestDTO(constant, dto);
        return constant;
    }

    public static ConstantResponseDTO map(Constant constant) {
        return toResponseDTO(constant);
    }
}
