package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.VariableConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

public class VariableConditionMapper {

    public static VariableCondition map(VariableConditionRequestDTO dto, VariableCondition variableCondition) {
        if (variableCondition == null) return null;
        variableCondition.setLabel(dto.getLabel());
        variableCondition.setDescription(dto.getDescription());
        variableCondition.setVariableName(dto.getVariableName());
        variableCondition.setToReturn(dto.getToReturn());
        variableCondition.setProduct(dto.getProduct());
        variableCondition.setBranch(dto.getBranch() != null ? dto.getBranch() : null);
        // Note: Les règles (ruleIds) doivent être gérées dans le service
        return variableCondition;
    }

    public static VariableCondition map(VariableConditionRequestDTO dto) {
        return map(dto, new VariableCondition());
    }

    public static VariableConditionResponseDTO map(VariableCondition variableCondition) {
        if (variableCondition == null) return null;
        VariableConditionResponseDTO variableConditionResponseDTO = (VariableConditionResponseDTO) VariableItemMapper.map(variableCondition);
        variableConditionResponseDTO.setCoverage(variableCondition.getCoverage());
        variableConditionResponseDTO.setRules(variableCondition.getRules() != null ?
                variableCondition.getRules().stream()
                        .map(RuleMapper::map)
                        .collect(Collectors.toSet()) :
                Collections.emptySet());
        return variableConditionResponseDTO;
    }

}
