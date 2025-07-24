package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FormulaRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.NumericFieldMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldMapper;

public class FormulaMapper {

    private static Object getRealVariable (VariableItem variableItem) {
        if (variableItem == null) return null;
        if (variableItem instanceof Formula) return FormulaMapper.map((Formula) variableItem);
        if (variableItem instanceof VariableCondition) return VariableConditionMapper.map((VariableCondition) variableItem);
        if (variableItem instanceof NumericField) return NumericFieldMapper.map((NumericField) variableItem);
        if (variableItem instanceof SelectField) return SelectFieldMapper.map((SelectField) variableItem);
        if (variableItem instanceof Constant) return ConstantMapper.map((Constant) variableItem);
        throw new IllegalArgumentException("Unknown variable item type: " + variableItem.getClass().getName());
    }

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
                .variables(
                        formula.getVariables().stream()
                                .map(FormulaMapper::getRealVariable)
                                .toList()
                )
                .build();
    }
}
