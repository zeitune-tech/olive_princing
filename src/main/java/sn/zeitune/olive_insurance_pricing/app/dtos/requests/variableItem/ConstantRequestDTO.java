package sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConstantRequestDTO extends VariableItemRequestDTO {
    @NotNull(message = "Value is required")
    Double value;
}
