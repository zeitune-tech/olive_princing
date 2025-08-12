package sn.zeitune.olive_insurance_pricing.app.mappers.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.NumericFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.mappers.VariableItemMapper;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

public class NumericFieldMapper {

    public static NumericField map(NumericFieldRequestDTO dto, NumericField field) {
        field.setLabel(dto.getLabel());
        field.setDescription(dto.getDescription());
        field.setVariableName(dto.getVariableName());
        field.setToReturn(dto.getToReturn());
        field.setProduct(dto.getProduct());
        field.setBranch(dto.getBranch());
        return field;
    }

    public static NumericField map(NumericFieldRequestDTO dto) {
        return map(dto, new NumericField());
    }

    public static NumericFieldResponseDTO map(NumericField field) {
        return (NumericFieldResponseDTO) VariableItemMapper.map(field);
    }
}
