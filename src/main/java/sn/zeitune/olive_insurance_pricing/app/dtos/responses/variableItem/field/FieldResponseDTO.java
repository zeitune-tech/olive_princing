package sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.VariableItemResponseDTO;

@SuperBuilder
@AllArgsConstructor
@Data
public abstract class FieldResponseDTO extends VariableItemResponseDTO {
}
