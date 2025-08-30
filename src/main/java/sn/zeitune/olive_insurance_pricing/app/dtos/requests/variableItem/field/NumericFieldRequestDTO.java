package sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.field;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NumericFieldRequestDTO extends FieldRequestDTO {
    @NonNull
    private Double minValue;
    @NonNull
    private Double maxValue;
}
