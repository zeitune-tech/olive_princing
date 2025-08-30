package sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.SelectFieldOptionValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.enums.SelectFieldOperator;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectConditionResponseDTO extends ConditionResponseDTO {
    SelectFieldOptionValueResponseDTO value;
    SelectFieldResponseDTO field;
    SelectFieldOperator operator;
}
