package sn.zeitune.olive_insurance_pricing.app.services.variableItem.variableCondition;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition.VariableConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition.VariableConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.RetrieveGenericService;

import java.util.UUID;

public interface VariableConditionService extends RetrieveGenericService<VariableCondition, VariableConditionRequestDTO, VariableConditionResponseDTO> {

    VariableConditionResponseDTO create(VariableConditionRequestDTO variableConditionDto, UUID managementEntity);
    VariableConditionResponseDTO update(UUID uuid, VariableConditionRequestDTO variableConditionDto, UUID managementEntity);
    void delete(UUID uuid, UUID managementEntity);

}
