package sn.zeitune.olive_insurance_pricing.app.mappers;


import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.field.Field;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.FieldMapper;

public class VariableItemMapper {

    private static VariableItemResponseDTO dtoForVariableItem (VariableItem variableItem) {
        if (variableItem instanceof Constant) {
            return ConstantMapper.map((Constant) variableItem);
        }
        if (variableItem instanceof Field) {
            return FieldMapper.map((Field) variableItem);
        }
        if (variableItem instanceof Formula) {
            return FormulaMapper.map((Formula) variableItem);
        }
        // Add other mappings for different VariableItem types if needed
        return null; // or throw an exception if appropriate

    }

    public static VariableItemResponseDTO map(VariableItem variableItem) {
        return dtoForVariableItem(variableItem);
    }
}
