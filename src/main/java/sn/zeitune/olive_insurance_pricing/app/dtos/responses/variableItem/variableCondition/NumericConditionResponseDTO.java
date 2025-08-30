package sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.enums.NumericOperator;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NumericConditionResponseDTO extends ConditionResponseDTO {
    NumericFieldResponseDTO field;
    NumericOperator operator;
    Double value;
    Double minValue;
    Double maxValue;
}
