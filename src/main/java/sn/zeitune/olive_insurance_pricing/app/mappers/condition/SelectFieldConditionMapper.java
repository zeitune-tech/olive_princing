package sn.zeitune.olive_insurance_pricing.app.mappers.condition;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.SelectFieldConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.SelectFieldConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.SelectCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldOptionValueMapper;

public class SelectFieldConditionMapper {

    public static SelectCondition map(SelectFieldConditionRequestDTO dto, SelectField field, SelectCondition selectCondition) {
        selectCondition.setSelectField(field);
        selectCondition.setSelectFieldOperator(dto.operator());
        return selectCondition;
    }

    public static SelectCondition map(SelectFieldConditionRequestDTO dto, SelectCondition selectCondition) {
//        throw new RuntimeException("Not Implemented");
        return map(dto, null, selectCondition);
    }

    public static SelectCondition map(SelectFieldConditionRequestDTO dto) {
        return map(dto, new SelectCondition());
    }

    public static SelectFieldConditionResponseDTO map(SelectCondition selectCondition) {
        return SelectFieldConditionResponseDTO.builder()
                .id(selectCondition.getUuid())
                .value(SelectFieldOptionValueMapper.map(selectCondition.getSelectFieldValue()))
                .field( selectCondition.getSelectField() != null ? SelectFieldMapper.map(selectCondition.getSelectField()) : null)
                .operator(selectCondition.getSelectFieldOperator())
                .build();
    }
}
