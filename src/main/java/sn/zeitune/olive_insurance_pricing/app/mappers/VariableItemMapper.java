package sn.zeitune.olive_insurance_pricing.app.mappers;


import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.field.Field;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.FieldMapper;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

public class VariableItemMapper {

    private static VariableItemResponseDTO dtoForVariableItem (VariableItem variableItem) {
        if (variableItem instanceof Constant constant)
            return ConstantMapper.map(constant);

        if (variableItem instanceof Field field)
            return FieldMapper.map(field);

        if (variableItem instanceof VariableCondition variableCondition)
            return VariableConditionMapper.map(variableCondition);

        if (variableItem instanceof Formula formula)
            return FormulaMapper.map(formula);

        // Add other mappings for different VariableItem types if needed
        return null; // or throw an exception if appropriate

    }

    public static VariableItemResponseDTO map(VariableItem variableItem) {
        return dtoForVariableItem(variableItem);
    }
}
