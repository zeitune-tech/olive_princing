package sn.zeitune.olive_insurance_pricing.app.mappers.condition;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.SelectFieldConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.SelectFieldConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.SelectFieldCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldOptionValueMapper;

public class SelectFieldConditionMapper {

    public static SelectFieldCondition map(SelectFieldConditionRequestDTO dto, SelectField field, SelectFieldCondition selectFieldCondition) {
        selectFieldCondition.setSelectField(field);
        selectFieldCondition.setSelectFieldOperator(dto.operator());
        return selectFieldCondition;
    }

    public static SelectFieldCondition map(SelectFieldConditionRequestDTO dto, SelectFieldCondition selectFieldCondition) {
//        throw new RuntimeException("Not Implemented");
        return map(dto, null, selectFieldCondition);
    }

    public static SelectFieldCondition map(SelectFieldConditionRequestDTO dto) {
        return map(dto, new SelectFieldCondition());
    }

    public static SelectFieldConditionResponseDTO map(SelectFieldCondition selectFieldCondition) {
        return SelectFieldConditionResponseDTO.builder()
                .id(selectFieldCondition.getUuid())
                .value(SelectFieldOptionValueMapper.map(selectFieldCondition.getSelectFieldValue()))
                .field( selectFieldCondition.getSelectField() != null ? SelectFieldMapper.map(selectFieldCondition.getSelectField()) : null)
                .operator(selectFieldCondition.getSelectFieldOperator())
                .build();
    }
}
