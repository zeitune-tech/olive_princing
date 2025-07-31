package sn.zeitune.olive_insurance_pricing.app.mappers.condition;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.NumericalConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.NumericalConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.NumericCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.NumericFieldMapper;

public class NumericalConditionMapper {

    public static NumericCondition map(NumericalConditionRequestDTO dto, NumericField field, NumericCondition numericCondition) {
        numericCondition.setNumericValue(dto.value());
        numericCondition.setNumericField(field);
        numericCondition.setNumericOperator(dto.numericOperator());
        return numericCondition;
    }

    public static NumericCondition map(NumericalConditionRequestDTO dto, NumericCondition numericCondition) {
//        throw new RuntimeException("Not Implemented");
        return map(dto, null, numericCondition);
    }

    public static NumericCondition map(NumericalConditionRequestDTO dto) {
        return map(dto, new NumericCondition());
    }

    public static NumericalConditionResponseDTO map(NumericCondition numericCondition) {
        return NumericalConditionResponseDTO.builder()
                .id(numericCondition.getUuid())
                .value(numericCondition.getNumericValue())
                .numericField( numericCondition.getNumericField() != null ? NumericFieldMapper.map(numericCondition.getNumericField()) : null)
                .operator(numericCondition.getNumericOperator())
                .build();
    }
}
