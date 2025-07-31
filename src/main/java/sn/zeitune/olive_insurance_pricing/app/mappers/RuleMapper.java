package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.RuleRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.RuleResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Rule;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.NumericCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.SelectCondition;
import sn.zeitune.olive_insurance_pricing.app.mappers.condition.NumericalConditionMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.condition.SelectFieldConditionMapper;

import java.util.stream.Collectors;

public class RuleMapper {

    public static Rule map(RuleRequestDTO dto, Rule rule) {
        rule.setValue(dto.value());
        rule.setLabel(dto.label());
        rule.setName(dto.name());
        return rule;
    }

    public static RuleResponseDTO map(Rule rule) {
        return RuleResponseDTO.builder()
                .id(rule.getUuid())
                .label(rule.getLabel())
                .name(rule.getName())
                .value(rule.getValue())
                .conditions(
                        rule.getConditions() != null ?
                            rule.getConditions().stream().map( condition -> condition instanceof NumericCondition ? NumericalConditionMapper.map((NumericCondition) condition) : SelectFieldConditionMapper.map((SelectCondition) condition)).collect(Collectors.toSet())
                            : null
                )
                .build();
    }
}
