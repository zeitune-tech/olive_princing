package sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class ConditionRequestDTO {
    @NotNull(message = "Field UUID is required")
    UUID fieldId;
}
