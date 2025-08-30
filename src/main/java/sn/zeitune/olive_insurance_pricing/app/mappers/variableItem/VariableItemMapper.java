package sn.zeitune.olive_insurance_pricing.app.mappers.variableItem;


import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.VariableItemRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition.VariableConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.Field;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.field.FieldMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.field.NumericFieldMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.field.SelectFieldMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.variableCondition.VariableConditionMapper;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

import java.time.LocalDate;

public class VariableItemMapper extends GenericMapper<VariableItem, VariableItemRequestDTO, VariableItemResponseDTO> {

    private static VariableItemMapper instance;

    private  VariableItemMapper() {}

    public static VariableItemMapper getInstance() {
        if (instance == null) {
            instance = new VariableItemMapper();
        }
        return instance;
    }

    public static class Utils {;
        public static VariableItemResponseDTO newResponseDTO (VariableItem variableItem) {
            if (variableItem instanceof Constant)
                return new ConstantResponseDTO();

            if (variableItem instanceof Field field)
                return FieldMapper.Utils.newResponseDTO(field);

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

        public static VariableItemResponseDTO getVariableItemResponseDTO (VariableItem variableItem) {
            if (variableItem == null) return null;
            if (variableItem instanceof Formula) return FormulaMapper.getInstance().map((Formula) variableItem);
            if (variableItem instanceof VariableCondition) return VariableConditionMapper.getInstance().map((VariableCondition) variableItem);
            if (variableItem instanceof NumericField) return NumericFieldMapper.getInstance().map((NumericField) variableItem);
            if (variableItem instanceof SelectField) return SelectFieldMapper.getInstance().map((SelectField) variableItem);
            if (variableItem instanceof Constant) return ConstantMapper.getInstance().map((Constant) variableItem);
            return VariableItemMapper.getInstance().map(variableItem);
        }
    }

    public void putRequestValue (VariableItemRequestDTO dto, VariableItem variableItem) {
        if (dto == null || variableItem == null) return;
        variableItem.setLabel(dto.getLabel());
        variableItem.setDescription(dto.getDescription());
        variableItem.setVariableName(dto.getVariableName());
        variableItem.setToReturn(dto.getToReturn());
        variableItem.setCoverage(dto.getCoverage());
        variableItem.setDateEffective(dto.getDateEffective() != null ? dto.getDateEffective() : LocalDate.now());
        // Note: The management entity is not set here, it should be handled in the service layer
    }

    /**
     * Maps a VariableItem entity to a VariableItemResponseDTO.
     *
     * @param variableItem the VariableItem entity to map
     * @return the mapped VariableItemResponseDTO
     */
    public VariableItemResponseDTO map(VariableItem variableItem) {
        if (variableItem == null) return null;
        VariableItemResponseDTO dto = Utils.newResponseDTO(variableItem);
        dto.setId(variableItem.getUuid());
        dto.setLabel(variableItem.getLabel());
        dto.setDescription(variableItem.getDescription());
        dto.setVariableName(variableItem.getVariableName());
        dto.setType(Utils.getTypeOfVariable(variableItem));
        dto.setToReturn(variableItem.getToReturn());
//        dto.setManagementEntity(variableItem.getManagementEntity() != null ? variableItem.getManagementEntity() : null);
        dto.setProduct(variableItem.getPricingType().getProduct());
        dto.setBranch(variableItem.getPricingType().getBranch());
        dto.setCoverage(variableItem.getCoverage());
        dto.setPricingType(variableItem.getPricingType() != null ? variableItem.getPricingType().getUuid() : null);
        dto.setCreatedAt(variableItem.getCreatedAt());
        dto.setUpdatedAt(variableItem.getUpdatedAt());
        dto.setDateEffective(variableItem.getDateEffective());
        return dto;
    }
}
