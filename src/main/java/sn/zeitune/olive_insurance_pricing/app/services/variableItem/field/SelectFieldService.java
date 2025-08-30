package sn.zeitune.olive_insurance_pricing.app.services.variableItem.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.field.SelectFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.RetrieveGenericService;

import java.util.UUID;

public interface SelectFieldService extends RetrieveGenericService<SelectField, SelectFieldRequestDTO, SelectFieldResponseDTO> {

    SelectFieldResponseDTO create(SelectFieldRequestDTO numericFieldRequestDTO, UUID managementEntity);
    SelectFieldResponseDTO update(UUID uuid, SelectFieldRequestDTO numericFieldRequestDTO, UUID managementEntity);
    void delete(UUID uuid, UUID managementEntity);

}
