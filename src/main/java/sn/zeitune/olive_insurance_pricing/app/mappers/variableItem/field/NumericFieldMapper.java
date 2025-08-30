package sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.field.NumericFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.GenericMapper;

public class NumericFieldMapper extends GenericMapper<NumericField, NumericFieldRequestDTO, NumericFieldResponseDTO>  {

    private static NumericFieldMapper instance;

    private NumericFieldMapper() {}

    public static NumericFieldMapper getInstance() {
        if (instance == null) {
            instance = new NumericFieldMapper();
        }
        return instance;
    }

    public void putRequestValue(NumericFieldRequestDTO dto, NumericField field) {
        FieldMapper.getInstance().putRequestValue(dto, field);
    }

    public NumericFieldResponseDTO map(NumericField field) {
        return (NumericFieldResponseDTO) FieldMapper.getInstance().map(field);
    }
}
