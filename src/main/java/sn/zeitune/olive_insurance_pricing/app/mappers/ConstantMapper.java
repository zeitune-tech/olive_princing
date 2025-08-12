package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

public class ConstantMapper {

    public static Constant map(ConstantRequestDTO dto, Constant constant) {
        constant.setLabel(dto.getLabel());
        constant.setDescription(dto.getDescription());
        constant.setVariableName(dto.getVariableName());
        constant.setValue(dto.getValue());
        constant.setToReturn(dto.getToReturn());
        constant.setProduct(dto.getProduct());
        constant.setBranch(dto.getBranch());
        return constant;
    }

    public static Constant map(ConstantRequestDTO requestDTO) {
        return map(requestDTO, new Constant());
    }

    public static ConstantResponseDTO map(Constant constant) {
        ConstantResponseDTO dto = (ConstantResponseDTO) VariableItemMapper.map(constant);
        dto.setValue(constant.getValue());
        return dto;
    }
}
