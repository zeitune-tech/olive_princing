package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

public class ConstantMapper {

    public static void putRequestValue(ConstantRequestDTO dto, Constant constant) {
        if (constant == null) return;
        VariableItemMapper.putRequestValue(dto, constant);
        constant.setValue(dto.getValue());
    }

    public static ConstantResponseDTO map(Constant constant) {
        if (constant == null) return null;
        ConstantResponseDTO dto = (ConstantResponseDTO) VariableItemMapper.map(constant);
        dto.setValue(constant.getValue());
        return dto;
    }
}
