package sn.zeitune.olive_insurance_pricing.app.mappers;


import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.field.Field;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.FieldMapper;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

public class VariableItemMapper {

    private static VariableItemResponseDTO createResponseDTO (VariableItem variableItem) {
        if (variableItem instanceof Constant)
            return new ConstantResponseDTO();

        if (variableItem instanceof Field field)
            return FieldMapper.createResponseDTO(field);

        if (variableItem instanceof VariableCondition)
            return new VariableConditionResponseDTO();

        if (variableItem instanceof Formula)
            return new FormulaResponseDTO();

        return null;
    }

    private static TypeOfVariable getTypeOfVariable(VariableItem variableItem) {
        if (variableItem instanceof Constant) return TypeOfVariable.CONSTANT;
        if (variableItem instanceof Field) return FieldMapper.getTypeOfField((Field) variableItem);
        if (variableItem instanceof VariableCondition) return TypeOfVariable.VARIABLE_CONDITION;
        if (variableItem instanceof Formula) return TypeOfVariable.FORMULA;
        throw new IllegalArgumentException("Unknown variable item type: " + variableItem.getClass().getName());
    }

    public static VariableItemResponseDTO map(VariableItem variableItem) {
        VariableItemResponseDTO dto = createResponseDTO(variableItem);
        dto.setId(variableItem.getUuid());
        dto.setLabel(variableItem.getLabel());
        dto.setDescription(variableItem.getDescription());
        dto.setVariableName(variableItem.getVariableName());
        dto.setToReturn(variableItem.getToReturn());
        dto.setManagementEntity(variableItem.getManagementEntity() != null ? variableItem.getManagementEntity() : null);
        dto.setProduct(variableItem.getProduct());
        dto.setBranch(variableItem.getBranch() != null ? variableItem.getBranch() : null);
        dto.setType(getTypeOfVariable(variableItem));
        dto.setCreatedAt(variableItem.getCreatedAt());
        dto.setUpdatedAt(variableItem.getUpdatedAt());
        return dto;
    }
}
