package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.RuleRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.RuleResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Rule;

import java.util.stream.Collectors;

public class RuleMapper {

    public static Rule map(RuleRequestDTO dto, Rule rule) {
        rule.setLabel(dto.label());
        rule.setDescription(dto.description());
        rule.setVariableName(dto.variableName());
        rule.setValue(dto.value());
        rule.setToReturn(dto.toReturn());
        rule.setManagementEntity(dto.managementEntity());
        rule.setProduct(dto.product());
        rule.setCoverage(dto.coverage());
        return rule;
    }

    public static RuleResponseDTO map(Rule rule) {
        return RuleResponseDTO.builder()
                .id(rule.getUuid())
                .label(rule.getLabel())
                .description(rule.getDescription())
                .variableName(rule.getVariableName())
                .value(rule.getValue())
                .toReturn(rule.getToReturn())
                .managementEntity(rule.getManagementEntity())
                .product(rule.getProduct())
                .coverage(rule.getCoverage())
                .conditions(rule.getConditions() != null ? 
                    rule.getConditions().stream()
                        .map(ConditionMapper::map)
                        .collect(Collectors.toSet()) : null)
                .build();
    }
}
