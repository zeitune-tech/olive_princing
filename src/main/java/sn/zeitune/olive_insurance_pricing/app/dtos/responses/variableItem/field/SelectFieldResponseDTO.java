package sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectFieldResponseDTO extends FieldResponseDTO {
    SelectFieldOptionResponseDTO options;
}
