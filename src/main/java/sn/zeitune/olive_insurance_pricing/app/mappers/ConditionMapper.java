package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Condition;
import sn.zeitune.olive_insurance_pricing.app.entities.Field;

public class ConditionMapper {

    public static Condition map(ConditionRequestDTO dto, Field field, Condition condition) {
        condition.setValue(dto.value());
        condition.setField(field);
        condition.setOperator(dto.operator());
        return condition;
    }

    public static ConditionResponseDTO map(Condition condition) {
        return ConditionResponseDTO.builder()
                .id(condition.getId())
                .value(condition.getValue())
                .field(condition.getField() != null ? FieldMapper.map(condition.getField()) : null)
                .operator(condition.getOperator())
                .build();
    }
}
