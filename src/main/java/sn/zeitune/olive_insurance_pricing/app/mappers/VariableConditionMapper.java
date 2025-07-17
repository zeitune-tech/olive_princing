package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.VariableConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;

import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

public class VariableConditionMapper {

    public static VariableCondition map(VariableConditionRequestDTO dto, VariableCondition variableCondition) {
        if (variableCondition == null) return null;
        variableCondition.setLabel(dto.label());
        variableCondition.setDescription(dto.description());
        variableCondition.setVariableName(dto.variableName());
        variableCondition.setToReturn(dto.toReturn());
        variableCondition.setManagementEntity(dto.managementEntity() != null ? dto.managementEntity() : null);
        variableCondition.setProduct(dto.product());
        variableCondition.setCoverage(dto.coverage());
        // Note: Les règles (ruleIds) doivent être gérées dans le service
        return variableCondition;
    }

    public static VariableCondition map(VariableConditionRequestDTO dto) {

        return map(dto, new VariableCondition());
    }


    public static VariableConditionResponseDTO map(VariableCondition variableCondition) {
        if (variableCondition == null)
            return null;
        return VariableConditionResponseDTO.builder()
                .uuid(variableCondition.getUuid())
                .label(variableCondition.getLabel())
                .description(variableCondition.getDescription())
                .variableName(variableCondition.getVariableName())
                .toReturn(variableCondition.getToReturn())
                .managementEntity(variableCondition.getManagementEntity() != null ? variableCondition.getManagementEntity() : null)
                .product(variableCondition.getProduct())
                .coverage(variableCondition.getCoverage())
                .rules(variableCondition.getRules() != null ?
                        variableCondition.getRules().stream()
                                .map(RuleMapper::map)
                                .collect(Collectors.toSet()) :
                        Collections.emptySet())
                .build();
    }

}
