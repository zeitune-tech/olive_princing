package sn.zeitune.olive_insurance_pricing.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.enums.Operator;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ConditionDto {
    private Long id;
    private Double value;
    private FieldDto field;
    private Operator operator;
}
