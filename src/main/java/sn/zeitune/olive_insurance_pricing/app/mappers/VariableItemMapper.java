package sn.zeitune.olive_insurance_pricing.app.mappers;


import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.field.Field;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.FieldMapper;

public class VariableItemMapper {

//    public static VariableItem map(VariableItemRequestDTO dto, VariableItem variableItem) {
//        variableItem.setLabel(dto.label());
//        variableItem.setDescription(dto.description());
//        variableItem.setVariableName(dto.variableName());
//        variableItem.setToReturn(dto.toReturn());
//        variableItem.setManagementEntity(dto.managementEntity());
//        variableItem.setProduct(dto.product());
//        variableItem.setCoverage(dto.coverage());
//        return variableItem;
//    }

//    public static VariableItem map(VariableItemRequestDTO dto) {
//        return map(dto, new VariableItem());
//    }

    private static Object dtoForVariableItem (VariableItem variableItem) {
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

    public static Object map(VariableItem variableItem) {
        return dtoForVariableItem(variableItem);
    }
}
