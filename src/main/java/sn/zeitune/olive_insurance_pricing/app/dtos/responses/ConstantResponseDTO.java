package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConstantResponseDTO extends VariableItemResponseDTO {
    Double value;
}
