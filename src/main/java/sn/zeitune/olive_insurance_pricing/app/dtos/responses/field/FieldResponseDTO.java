package sn.zeitune.olive_insurance_pricing.app.dtos.responses.field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.UUID;

@AllArgsConstructor
//@NoArgsConstructor
@Data
public abstract class FieldResponseDTO extends VariableItemResponseDTO {}
