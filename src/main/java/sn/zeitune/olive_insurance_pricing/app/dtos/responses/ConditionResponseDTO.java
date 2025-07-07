package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.Builder;
import sn.zeitune.olive_insurance_pricing.enums.Operator;

@Builder
public record ConditionResponseDTO(
        Long id,
        Double value,
        FieldResponseDTO field,
        Operator operator
) {}
