package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Condition;
import sn.zeitune.olive_insurance_pricing.app.entities.Field;
import sn.zeitune.olive_insurance_pricing.app.entities.NumericField;
import sn.zeitune.olive_insurance_pricing.app.entities.SelectField;

public class ConditionMapper {

    public static Condition map(ConditionRequestDTO dto, Field field, Condition condition) {
        condition.setValue(dto.value());
        condition.setField(field);
        condition.setOperator(dto.operator());
        return condition;
    }

    public static Condition map(ConditionRequestDTO dto, Condition condition) {
        throw new RuntimeException("Not Implemented");
//        return map(dto, null, condition);
    }

    public static Condition map(ConditionRequestDTO dto) {
        return map(dto, new Condition());
    }

    public static ConditionResponseDTO map(Condition condition) {

        if (condition.getField() instanceof NumericField)
            return ConditionResponseDTO.builder()
                    .id(condition.getUuid())
                    .isNumeric(true)
                    .value(condition.getValue())
                    .numericField(condition.getField() != null ? NumericFieldMapper.map( (NumericField) condition.getField()) : null)
                    .operator(condition.getOperator())
                    .build();
        else
            return ConditionResponseDTO.builder()
                    .id(condition.getUuid())
                    .isNumeric(false)
                    .value(condition.getValue())
                    .selectField(condition.getField() != null ? SelectFieldMapper.map( (SelectField) condition.getField()) : null)
                    .operator(condition.getOperator())
                    .build();
    }
}
