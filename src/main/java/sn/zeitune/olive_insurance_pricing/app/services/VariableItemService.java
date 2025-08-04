package sn.zeitune.olive_insurance_pricing.app.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;

import java.util.List;
import java.util.UUID;

public interface VariableItemService {

    VariableItemResponseDTO findByUuid(UUID uuid);

    List<VariableItemResponseDTO> findByProduct(UUID product);

    List<VariableItemResponseDTO> searchByLabel(String label);

    boolean existsByUuid(UUID uuid);

    VariableItem getEntityByUuid(@NotNull(message = "VariableItem UUID is required") UUID uuid);

    VariableItem findByVariableName(String variable);

    List<VariableItemResponseDTO> findAll(UUID managementEntity);
}
