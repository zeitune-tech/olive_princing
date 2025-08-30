package sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.variableCondition;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition.RuleRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition.RuleResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.Rule;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.condition.NumericCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.condition.SelectCondition;

import java.util.stream.Collectors;

public class RuleMapper {

    public static Rule map(RuleRequestDTO dto, Rule rule) {
        rule.setValue(dto.getValue());
        rule.setLabel(dto.getLabel());
        rule.setName(dto.getName());
        return rule;
    }

    public static RuleResponseDTO map(Rule rule) {
        if (rule == null) {
            return null; // Handle null case if necessary
        }
        RuleResponseDTO ruleResponseDTO = new RuleResponseDTO();
        ruleResponseDTO.setId(rule.getUuid());
        ruleResponseDTO.setLabel(rule.getLabel());
        ruleResponseDTO.setName(rule.getName());
        ruleResponseDTO.setValue(rule.getValue());
        ruleResponseDTO.setConditions(
                rule.getConditions() != null ?
                        rule.getConditions().stream()
                                .map(condition -> condition instanceof NumericCondition ?
                                        NumericConditionMapper.map((NumericCondition) condition) :
                                        SelectConditionMapper.map((SelectCondition) condition))
                                .collect(Collectors.toSet())
                        : null
        );
        return ruleResponseDTO;

    }
}
