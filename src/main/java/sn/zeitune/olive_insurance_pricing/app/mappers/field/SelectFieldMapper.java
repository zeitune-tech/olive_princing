package sn.zeitune.olive_insurance_pricing.app.mappers.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.mappers.VariableItemMapper;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

public class SelectFieldMapper {

    public static void putRequestValue(SelectFieldRequestDTO dto, SelectField field) {
        FieldMapper.putRequestValue(dto, field);
    }

    public static SelectFieldResponseDTO map(SelectField field) {
        SelectFieldResponseDTO dto  = (SelectFieldResponseDTO) FieldMapper.map(field);
        dto.setOptions(SelectFieldOptionsMapper.map(field.getOptions()));
        return dto;
    }
}
