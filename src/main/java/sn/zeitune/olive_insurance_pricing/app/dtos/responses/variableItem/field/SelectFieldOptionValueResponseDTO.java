package sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectFieldOptionValueResponseDTO {
    private UUID id;
    private String label;
    private String name;
    private String group;
}
