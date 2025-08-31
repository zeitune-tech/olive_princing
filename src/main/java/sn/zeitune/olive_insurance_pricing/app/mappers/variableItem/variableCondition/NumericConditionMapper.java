package sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.variableCondition;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition.NumericConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition.NumericConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.condition.NumericCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.field.NumericFieldMapper;

public class NumericConditionMapper {

    public static void putRequestValue(NumericConditionRequestDTO dto, NumericField field, NumericCondition numericCondition) {
        if (numericCondition == null || dto == null || field == null) return;
        if (dto.getMaxValue() != 0 || dto.getMinValue() != 0) {
            numericCondition.setNumericValue(Double.NaN);
        }else {
            numericCondition.setNumericValue(dto.getValue());
        }
        numericCondition.setMaximum(dto.getMaxValue());
        numericCondition.setMinimum(dto.getMinValue());
        numericCondition.setNumericOperator(dto.getNumericOperator());
        numericCondition.setNumericField(field);
    }

    public static NumericConditionResponseDTO map(NumericCondition numericCondition) {
        if (numericCondition == null) return null;
        NumericConditionResponseDTO numericConditionResponseDTO = new NumericConditionResponseDTO();
        numericConditionResponseDTO.setId(numericCondition.getUuid());
        numericConditionResponseDTO.setValue(Double.isNaN(numericCondition.getNumericValue()) ? 0. : numericCondition.getNumericValue());
        numericConditionResponseDTO.setMinValue(numericCondition.getMinimum());
        numericConditionResponseDTO.setMaxValue(numericCondition.getMaximum());
        numericConditionResponseDTO.setField(
                numericCondition.getNumericField() != null ? NumericFieldMapper.getInstance().map(numericCondition.getNumericField()) : null);
        numericConditionResponseDTO.setOperator(numericCondition.getNumericOperator());
        return numericConditionResponseDTO;
    }
}