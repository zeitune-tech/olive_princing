package sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.variableCondition;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition.VariableConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition.VariableConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.GenericMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.VariableItemMapper;

import java.util.Collections;
import java.util.stream.Collectors;

public class VariableConditionMapper extends GenericMapper<VariableCondition, VariableConditionRequestDTO, VariableConditionResponseDTO> {

    private static VariableConditionMapper instance;

    private  VariableConditionMapper() {}

    public static VariableConditionMapper getInstance() {
        if (instance == null) {
            instance = new VariableConditionMapper();
        }
        return instance;
    }

    public void putRequestValue(VariableConditionRequestDTO dto, VariableCondition variableCondition) {
        if (variableCondition == null || dto == null) return;
        VariableItemMapper.getInstance().putRequestValue(dto, variableCondition);
        // Note: Les règles (ruleIds) doivent être gérées dans le service
    }

    public VariableConditionResponseDTO map(VariableCondition variableCondition) {
        if (variableCondition == null) return null;
        VariableConditionResponseDTO variableConditionResponseDTO = (VariableConditionResponseDTO) VariableItemMapper.getInstance().map(variableCondition);
        variableConditionResponseDTO.setRules(variableCondition.getRules() != null ?
                variableCondition.getRules().stream()
                        .map(RuleMapper::map)
                        .collect(Collectors.toSet()) :
                Collections.emptySet());
        return variableConditionResponseDTO;
    }

}
