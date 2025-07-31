package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;

public class ConstantMapper {

    public static Constant map(ConstantRequestDTO dto, Constant constant) {
        constant.setLabel(dto.getLabel());
        constant.setDescription(dto.getDescription());
        constant.setVariableName(dto.getVariableName());
        constant.setValue(dto.getValue());
        constant.setToReturn(dto.getToReturn());
        constant.setManagementEntity(dto.getManagementEntity());
        constant.setProduct(dto.getProduct());
        constant.setBranch(dto.getBranch());
        return constant;
    }

    public static Constant map(ConstantRequestDTO requestDTO) {
        return map(requestDTO, new Constant());
    }

    public static ConstantResponseDTO map(Constant constant) {
        ConstantResponseDTO dto = new ConstantResponseDTO();
        dto.setLabel(constant.getLabel());
        dto.setDescription(constant.getDescription());
        dto.setVariableName(constant.getVariableName());
        dto.setValue(constant.getValue());
        dto.setToReturn(constant.getToReturn());
        dto.setManagementEntity(constant.getManagementEntity());
        dto.setProduct(constant.getProduct());
        dto.setBranch(constant.getBranch());
        dto.setId(constant.getUuid());
        return dto;
    }
}
