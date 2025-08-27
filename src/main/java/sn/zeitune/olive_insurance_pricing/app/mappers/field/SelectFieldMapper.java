package sn.zeitune.olive_insurance_pricing.app.mappers.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

public class SelectFieldMapper {

    public static SelectField map(SelectFieldRequestDTO dto, SelectField field) {
        field.setLabel(dto.getLabel());
        field.setDescription(dto.getDescription());
        field.setVariableName(dto.getVariableName());
        field.setToReturn(dto.getToReturn());
        field.setProduct(dto.getProduct());
        field.setBranch(dto.getBranch());
        return field;
    }

    public static SelectField map(SelectFieldRequestDTO dto) {
        return map(dto, new SelectField());
    }

    public static SelectFieldResponseDTO map(SelectField field) {
        if (field == null) {
            return null; // Handle null case if necessary
        }
        SelectFieldResponseDTO ResponseDTO  = new SelectFieldResponseDTO();
        ResponseDTO.setId(field.getUuid());
        ResponseDTO.setLabel(field.getLabel());
        ResponseDTO.setDescription(field.getDescription());
        ResponseDTO.setVariableName(field.getVariableName());
        ResponseDTO.setToReturn(field.getToReturn());
        ResponseDTO.setManagementEntity(field.getManagementEntity());
        ResponseDTO.setProduct(field.getProduct());
        ResponseDTO.setBranch(field.getBranch());
        ResponseDTO.setOptions(SelectFieldOptionsMapper.map(field.getOptions()));
        ResponseDTO.setType(TypeOfVariable.SELECT_FIELD);
        return ResponseDTO;
    }
}
