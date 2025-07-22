package sn.zeitune.olive_insurance_pricing.app.mappers.condition;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.NumericalConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.NumericalConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.NumericalCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.NumericFieldMapper;

public class NumericalConditionMapper {

    public static NumericalCondition map(NumericalConditionRequestDTO dto, NumericField field, NumericalCondition numericalCondition) {
        numericalCondition.setNumericValue(dto.value());
        numericalCondition.setNumericField(field);
        numericalCondition.setNumericOperator(dto.numericOperator());
        return numericalCondition;
    }

    public static NumericalCondition map(NumericalConditionRequestDTO dto, NumericalCondition numericalCondition) {
//        throw new RuntimeException("Not Implemented");
        return map(dto, null, numericalCondition);
    }

    public static NumericalCondition map(NumericalConditionRequestDTO dto) {
        return map(dto, new NumericalCondition());
    }

    public static NumericalConditionResponseDTO map(NumericalCondition numericalCondition) {
        return NumericalConditionResponseDTO.builder()
                .id(numericalCondition.getUuid())
                .value(numericalCondition.getNumericValue())
                .numericField( numericalCondition.getNumericField() != null ? NumericFieldMapper.map(numericalCondition.getNumericField()) : null)
                .operator(numericalCondition.getNumericOperator())
                .build();
    }
}
