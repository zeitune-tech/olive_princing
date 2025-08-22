package sn.zeitune.olive_insurance_pricing.app.mappers;


import sn.zeitune.olive_insurance_pricing.app.dtos.requests.VariableItemRequestDTO;
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

    public static class Utils {;
        private static VariableItemResponseDTO createResponseDTO (VariableItem variableItem) {
            if (variableItem instanceof Constant)
                return new ConstantResponseDTO();

            if (variableItem instanceof Field field)
                return FieldMapper.Utils.createResponseDTO(field);

            if (variableItem instanceof VariableCondition)
                return new VariableConditionResponseDTO();

            if (variableItem instanceof Formula)
                return new FormulaResponseDTO();

            return null;
        }
        public static TypeOfVariable getTypeOfVariable(VariableItem variableItem) {
            if (variableItem instanceof Constant) return TypeOfVariable.CONSTANT;
            if (variableItem instanceof Field) return FieldMapper.Utils.getTypeOfField((Field) variableItem);
            if (variableItem instanceof VariableCondition) return TypeOfVariable.VARIABLE_CONDITION;
            if (variableItem instanceof Formula) return TypeOfVariable.FORMULA;
            throw new IllegalArgumentException("Unknown variable item type: " + variableItem.getClass().getName());
        }
    }

    public static void putRequestValue (VariableItemRequestDTO dto, VariableItem variableItem) {
        if (dto == null || variableItem == null) return;
        variableItem.setLabel(dto.getLabel());
        variableItem.setDescription(dto.getDescription());
        variableItem.setVariableName(dto.getVariableName());
        variableItem.setToReturn(dto.getToReturn());
        variableItem.setProduct(dto.getProduct());
        variableItem.setBranch(dto.getBranch());
        variableItem.setCoverage(dto.getCoverage());
        // Note: The management entity is not set here, it should be handled in the service layer
    }

    /**
     * Maps a VariableItem entity to a VariableItemResponseDTO.
     *
     * @param variableItem the VariableItem entity to map
     * @return the mapped VariableItemResponseDTO
     */
    public static VariableItemResponseDTO map(VariableItem variableItem) {
        if (variableItem == null) return null;
        VariableItemResponseDTO dto = Utils.createResponseDTO(variableItem);
        dto.setId(variableItem.getUuid());
        dto.setLabel(variableItem.getLabel());
        dto.setDescription(variableItem.getDescription());
        dto.setVariableName(variableItem.getVariableName());
        dto.setType(Utils.getTypeOfVariable(variableItem));
        dto.setToReturn(variableItem.getToReturn());
        dto.setManagementEntity(variableItem.getManagementEntity() != null ? variableItem.getManagementEntity() : null);
        dto.setProduct(variableItem.getProduct());
        dto.setBranch(variableItem.getBranch());
        dto.setCoverage(variableItem.getCoverage());
        dto.setPricingType(variableItem.getPricingType() != null ? variableItem.getPricingType().getUuid() : null);
        dto.setCreatedAt(variableItem.getCreatedAt());
        dto.setUpdatedAt(variableItem.getUpdatedAt());
        return dto;
    }
}
