package sn.zeitune.olive_insurance_pricing.app.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormulaRequestDTO extends VariableItemRequestDTO {
    @NotNull(message = "Expression is required")
    @NotBlank(message = "Expression must not be blank")
    String expression;
    Set<UUID> variables;
    UUID coverage;
}
