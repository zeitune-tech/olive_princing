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
    @NotNull(message = "Coverage is required")
    @NotBlank(message = "Coverage must not be blank")
    UUID coverage;
    @NotNull(message = "Pricing type is required")
    @NotBlank(message = "Pricing type must not be blank")
    UUID pricingType;

}
