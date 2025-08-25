package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

public class ConstantMapper extends GenericMapper<Constant, ConstantRequestDTO, ConstantResponseDTO> {

    private static ConstantMapper instance;

    private  ConstantMapper() {}

    public static ConstantMapper getInstance() {
        if (instance == null) {
            instance = new ConstantMapper();
        }
        return instance;
    }

    public void putRequestValue(ConstantRequestDTO dto, Constant constant) {
        if (constant == null) return;
        VariableItemMapper.getInstance().putRequestValue(dto, constant);
        constant.setValue(dto.getValue());
    }

    public ConstantResponseDTO map(Constant constant) {
        if (constant == null) return null;
        ConstantResponseDTO dto = (ConstantResponseDTO) VariableItemMapper.getInstance().map(constant);
        dto.setValue(constant.getValue());
        return dto;
    }
}
