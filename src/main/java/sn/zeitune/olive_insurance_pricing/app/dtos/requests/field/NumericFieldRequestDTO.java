package sn.zeitune.olive_insurance_pricing.app.dtos.requests.field;

import lombok.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.VariableItemRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.Field;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NumericFieldRequestDTO extends FieldRequestDTO {
    @NonNull
    private Double minValue;

    @NonNull
    private Double maxValue;
}
