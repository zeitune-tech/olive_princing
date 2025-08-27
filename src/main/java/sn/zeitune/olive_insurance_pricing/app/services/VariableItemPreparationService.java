package sn.zeitune.olive_insurance_pricing.app.services;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.VariableItemRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;

import java.util.UUID;

public interface VariableItemPreparationService {
    VariableItem prepareCreationEntity(VariableItemRequestDTO variableItemRequestDTO, VariableItem variableItem, UUID managementEntity);
    VariableItem prepareUpdatingEntity(VariableItemRequestDTO variableItemRequestDTO , VariableItem variableItem, UUID managementEntity);
}
