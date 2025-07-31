package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormulaResponseDTO extends VariableItemResponseDTO {
    UUID coverage;
    List<VariableItemResponseDTO> variables;
    String expression;
}
