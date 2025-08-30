package sn.zeitune.olive_insurance_pricing.app.services.variableItem.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.field.NumericFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.RetrieveGenericService;

import java.util.UUID;

public interface NumericFieldService extends RetrieveGenericService<NumericField, NumericFieldRequestDTO, NumericFieldResponseDTO> {

    NumericFieldResponseDTO create(NumericFieldRequestDTO numericFieldRequestDTO, UUID managementEntity);
    NumericFieldResponseDTO update(UUID uuid, NumericFieldRequestDTO numericFieldRequestDTO, UUID managementEntity);
    void delete(UUID uuid, UUID managementEntity);

}
