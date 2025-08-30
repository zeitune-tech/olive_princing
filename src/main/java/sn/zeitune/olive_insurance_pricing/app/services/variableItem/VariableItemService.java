package sn.zeitune.olive_insurance_pricing.app.services.variableItem;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.VariableItemRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.VariableItem;

public interface VariableItemService extends RetrieveGenericService<VariableItem, VariableItemRequestDTO, VariableItemResponseDTO> {
}
