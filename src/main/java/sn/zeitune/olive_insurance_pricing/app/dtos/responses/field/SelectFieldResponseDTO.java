package sn.zeitune.olive_insurance_pricing.app.dtos.responses.field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectFieldResponseDTO extends VariableItemResponseDTO {
    SelectFieldOptionResponseDTO options;
}
