package sn.zeitune.olive_insurance_pricing.app.services.variableItem.field;


import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.field.FieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.Field;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.RetrieveGenericService;

public interface FieldService extends RetrieveGenericService<Field, FieldRequestDTO, FieldResponseDTO> {

}
