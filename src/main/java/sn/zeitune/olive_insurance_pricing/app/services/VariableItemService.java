package sn.zeitune.olive_insurance_pricing.app.services;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.VariableItemRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;

public interface VariableItemService extends RetrieveGenericService<VariableItem, VariableItemRequestDTO, VariableItemResponseDTO> {
}
