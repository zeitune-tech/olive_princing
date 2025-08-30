package sn.zeitune.olive_insurance_pricing.app.services.variableItem;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.FormulaRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.Formula;

import java.util.UUID;

public interface FormulaService extends RetrieveGenericService<Formula, FormulaRequestDTO, FormulaResponseDTO> {

    FormulaResponseDTO create(FormulaRequestDTO formulaRequestDTO, UUID managementEntity);
    FormulaResponseDTO update(UUID uuid, FormulaRequestDTO formulaRequestDTO, UUID managementEntity);
    void delete(UUID uuid, UUID managementEntity);

}
