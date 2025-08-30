package sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormulaResponseDTO extends VariableItemResponseDTO {
    UUID coverage;
    List<VariableItemResponseDTO> variables;
    String expression;
}
