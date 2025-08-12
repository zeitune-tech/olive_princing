package sn.zeitune.olive_insurance_pricing.app.dtos.responses.field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectFieldResponseDTO extends FieldResponseDTO {
    SelectFieldOptionResponseDTO options;
}
