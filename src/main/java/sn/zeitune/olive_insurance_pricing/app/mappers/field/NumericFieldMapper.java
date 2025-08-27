package sn.zeitune.olive_insurance_pricing.app.mappers.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.NumericFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;

public class NumericFieldMapper {

    public static void putRequestValue(NumericFieldRequestDTO dto, NumericField field) {
        FieldMapper.putRequestValue(dto, field);
    }

    public static NumericFieldResponseDTO map(NumericField field) {
        return (NumericFieldResponseDTO) FieldMapper.map(field);
    }
}
