package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FormulaRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.NumericFieldMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldMapper;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

public class FormulaMapper {

    private static VariableItemResponseDTO getRealVariable (VariableItem variableItem) {
        if (variableItem == null) return null;
        if (variableItem instanceof Formula) return FormulaMapper.map((Formula) variableItem);
        if (variableItem instanceof VariableCondition) return VariableConditionMapper.map((VariableCondition) variableItem);
        if (variableItem instanceof NumericField) return NumericFieldMapper.map((NumericField) variableItem);
        if (variableItem instanceof SelectField) return SelectFieldMapper.map((SelectField) variableItem);
        if (variableItem instanceof Constant) return ConstantMapper.map((Constant) variableItem);
        throw new IllegalArgumentException("Unknown variable item type: " + variableItem.getClass().getName());
    }

    public static Formula map(FormulaRequestDTO dto, Formula formula) {
        formula.setLabel(dto.getLabel());
        formula.setDescription(dto.getDescription());
        formula.setVariableName(dto.getVariableName());
        formula.setExpression(dto.getExpression());
        formula.setToReturn(dto.getToReturn());
        formula.setProduct(dto.getProduct());
        formula.setBranch(dto.getBranch());
        formula.setCoverage(dto.getCoverage());
        return formula;
    }

    public static Formula map(FormulaRequestDTO dto) {
        return map(dto, new Formula());
    }

    public static FormulaResponseDTO map(Formula formula) {
        if (formula == null) return null; // Handle null case if necessary

        FormulaResponseDTO formulaResponseDTO = (FormulaResponseDTO) VariableItemMapper.map(formula);
        formulaResponseDTO.setExpression(formula.getExpression());
        formulaResponseDTO.setVariables(
                formula.getVariables().stream()
                        .map(FormulaMapper::getRealVariable)
                        .toList()
        );
        formulaResponseDTO.setCoverage(formula.getCoverage());
        return formulaResponseDTO;
    }
}
