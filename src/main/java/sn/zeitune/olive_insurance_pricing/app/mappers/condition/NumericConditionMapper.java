package sn.zeitune.olive_insurance_pricing.app.mappers.condition;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.NumericConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.NumericConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.NumericCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.NumericFieldMapper;

public class NumericConditionMapper {

    public static NumericCondition map(NumericConditionRequestDTO dto, NumericField field, NumericCondition numericCondition) {
        numericCondition.setNumericValue(dto.getValue());
        numericCondition.setNumericField(field);
        numericCondition.setNumericOperator(dto.getNumericOperator());
        return numericCondition;
    }

    public static NumericCondition map(NumericConditionRequestDTO dto, NumericCondition numericCondition) {
//        throw new RuntimeException("Not Implemented");
        return map(dto, null, numericCondition);
    }

    public static NumericCondition map(NumericConditionRequestDTO dto) {
        return map(dto, new NumericCondition());
    }

    public static NumericConditionResponseDTO map(NumericCondition numericCondition) {

        if (numericCondition == null) {
            return null; // Handle null case if necessary
        }
        NumericConditionResponseDTO numericConditionResponseDTO = new NumericConditionResponseDTO();
        numericConditionResponseDTO.setId(numericCondition.getUuid());
        numericConditionResponseDTO.setValue(numericCondition.getNumericValue());
        numericConditionResponseDTO.setNumericField(
                numericCondition.getNumericField() != null ? NumericFieldMapper.map(numericCondition.getNumericField()) : null);
        numericConditionResponseDTO.setOperator(numericCondition.getNumericOperator());
        return numericConditionResponseDTO;
    }
}
