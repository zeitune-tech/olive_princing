package sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConstantResponseDTO extends VariableItemResponseDTO {
    Double value;
}
