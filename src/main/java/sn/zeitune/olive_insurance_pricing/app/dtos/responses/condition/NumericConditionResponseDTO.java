package sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.enums.NumericOperator;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NumericConditionResponseDTO extends ConditionResponseDTO {
    NumericFieldResponseDTO field;
    NumericOperator operator;
    Double value;
}
