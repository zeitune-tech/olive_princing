package sn.zeitune.olive_insurance_pricing.app.services.variableItem;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.Constant;

import java.util.UUID;

public interface ConstantService extends RetrieveGenericService<Constant, ConstantRequestDTO, ConstantResponseDTO> {

    ConstantResponseDTO create(ConstantRequestDTO constantRequestDTO, UUID managementEntity);
    ConstantResponseDTO update(UUID uuid, ConstantRequestDTO constantRequestDTO, UUID managementEntity);
    void delete(UUID uuid, UUID managementEntity);

}