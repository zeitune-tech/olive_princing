package sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition;

import lombok.Builder;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.enums.NumericOperator;
import sn.zeitune.olive_insurance_pricing.enums.SelectFieldOperator;

import java.util.UUID;

@Builder
public record SelectFieldConditionResponseDTO(
        UUID id,
        SelectFieldOptionValueResponseDTO value,
        SelectFieldResponseDTO field,
        SelectFieldOperator operator
) {}
