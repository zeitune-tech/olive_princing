package sn.zeitune.olive_insurance_pricing.app.mappers;


import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;

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

    public static VariableItemResponseDTO map(VariableItem variableItem) {
        return VariableItemResponseDTO.builder()
                .id(variableItem.getUuid())
                .label(variableItem.getLabel())
                .description(variableItem.getDescription())
                .variableName(variableItem.getVariableName())
                .toReturn(variableItem.getToReturn())
                .managementEntity(variableItem.getManagementEntity())
                .product(variableItem.getProduct())
                .coverage(variableItem.getCoverage())
                .build();
    }
}
