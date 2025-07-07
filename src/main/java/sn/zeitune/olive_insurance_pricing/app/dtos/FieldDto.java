package sn.zeitune.olive_insurance_pricing.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FieldDto extends VariableItemDto {
    private FieldType type;
    private FieldValueDto value;
}
