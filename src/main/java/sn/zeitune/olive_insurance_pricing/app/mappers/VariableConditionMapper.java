package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.VariableConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

public class VariableConditionMapper {

    public static void putRequestValue(VariableConditionRequestDTO dto, VariableCondition variableCondition) {
        if (variableCondition == null || dto == null) return;
        VariableItemMapper.putRequestValue(dto, variableCondition);
        // Note: Les règles (ruleIds) doivent être gérées dans le service
    }

    public static VariableConditionResponseDTO map(VariableCondition variableCondition) {
        if (variableCondition == null) return null;
        VariableConditionResponseDTO variableConditionResponseDTO = (VariableConditionResponseDTO) VariableItemMapper.map(variableCondition);
        variableConditionResponseDTO.setRules(variableCondition.getRules() != null ?
                variableCondition.getRules().stream()
                        .map(RuleMapper::map)
                        .collect(Collectors.toSet()) :
                Collections.emptySet());
        return variableConditionResponseDTO;
    }

}
