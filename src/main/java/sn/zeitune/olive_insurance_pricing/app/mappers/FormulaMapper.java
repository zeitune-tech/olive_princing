package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FormulaRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;

public class FormulaMapper {

    public static Formula map(FormulaRequestDTO dto, Formula formula) {
        formula.setLabel(dto.label());
        formula.setDescription(dto.description());
        formula.setVariableName(dto.variableName());
        formula.setExpression(dto.expression());
        formula.setToReturn(dto.toReturn());
        formula.setManagementEntity(dto.managementEntity());
        formula.setProduct(dto.product());
        formula.setCoverage(dto.coverage());
        return formula;
    }

    public static Formula map(FormulaRequestDTO dto) {
        return map(dto, new Formula());
    }

    public static FormulaResponseDTO map(Formula formula) {
        return FormulaResponseDTO.builder()
                .id(formula.getUuid())
                .label(formula.getLabel())
                .description(formula.getDescription())
                .variableName(formula.getVariableName())
                .expression(formula.getExpression())
                .toReturn(formula.getToReturn())
                .managementEntity(formula.getManagementEntity())
                .product(formula.getProduct())
                .coverage(formula.getCoverage())
                .build();
    }
}
