package sn.zeitune.olive_insurance_pricing.app.dtos.requests.field;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectFieldRequestDTO extends FieldRequestDTO {
    UUID options;
}
