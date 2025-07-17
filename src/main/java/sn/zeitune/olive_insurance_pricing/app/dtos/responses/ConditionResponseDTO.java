package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.Builder;
import sn.zeitune.olive_insurance_pricing.enums.Operator;

import java.util.UUID;

@Builder
public record ConditionResponseDTO(
        UUID id,
        Double value,
        boolean isNumeric,
        SelectFieldResponseDTO selectField,
        NumericFieldResponseDTO numericField,
        Operator operator
) {}
