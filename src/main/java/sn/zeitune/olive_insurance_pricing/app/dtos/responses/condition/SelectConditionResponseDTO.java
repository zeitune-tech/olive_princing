package sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.enums.SelectFieldOperator;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectConditionResponseDTO extends ConditionResponseDTO {
    SelectFieldOptionValueResponseDTO value;
    SelectFieldResponseDTO field;
    SelectFieldOperator operator;
}
