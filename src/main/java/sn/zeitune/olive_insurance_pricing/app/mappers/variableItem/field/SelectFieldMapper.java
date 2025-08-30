package sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.field.SelectFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.GenericMapper;

public class SelectFieldMapper extends GenericMapper<SelectField, SelectFieldRequestDTO, SelectFieldResponseDTO> {

    private static SelectFieldMapper instance;

    private SelectFieldMapper() {}

    public static SelectFieldMapper getInstance() {
        if (instance == null) {
            instance = new SelectFieldMapper();
        }
        return instance;
    }

    public void putRequestValue(SelectFieldRequestDTO dto, SelectField field) {
        FieldMapper.getInstance().putRequestValue(dto, field);
    }

    public SelectFieldResponseDTO map(SelectField field) {
        SelectFieldResponseDTO dto  = (SelectFieldResponseDTO) FieldMapper.getInstance().map(field);
        dto.setOptions(SelectFieldOptionMapper.map(field.getOptions()));
        return dto;
    }
}
