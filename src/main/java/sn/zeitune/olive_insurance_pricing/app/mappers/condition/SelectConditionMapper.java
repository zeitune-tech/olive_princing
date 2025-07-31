package sn.zeitune.olive_insurance_pricing.app.mappers.condition;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.SelectConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.SelectConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.SelectCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldOptionValueMapper;

public class SelectConditionMapper {

    public static SelectCondition map(SelectConditionRequestDTO dto, SelectField field, SelectCondition selectCondition) {
        selectCondition.setSelectField(field);
        selectCondition.setSelectFieldOperator(dto.getOperator());
        return selectCondition;
    }

    public static SelectCondition map(SelectConditionRequestDTO dto, SelectCondition selectCondition) {
//        throw new RuntimeException("Not Implemented");
        return map(dto, null, selectCondition);
    }

    public static SelectCondition map(SelectConditionRequestDTO dto) {
        return map(dto, new SelectCondition());
    }

    public static SelectConditionResponseDTO map(SelectCondition selectCondition) {
        if (selectCondition == null) {
            return null; // Handle null case if necessary
        }
        SelectConditionResponseDTO dto = new SelectConditionResponseDTO();
        dto.setId(selectCondition.getUuid());
        dto.setValue(SelectFieldOptionValueMapper.map(selectCondition.getSelectFieldValue()));
        dto.setField(selectCondition.getSelectField() != null ? SelectFieldMapper.map(selectCondition.getSelectField()) : null);
        dto.setOperator(selectCondition.getSelectFieldOperator());
        return dto;
    }
}
