package sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition;

import lombok.Builder;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.enums.NumericOperator;

import java.util.UUID;

@Builder
public record NumericalConditionResponseDTO(
        UUID id,
        Double value,
        NumericFieldResponseDTO numericField,
        NumericOperator operator
) {}
