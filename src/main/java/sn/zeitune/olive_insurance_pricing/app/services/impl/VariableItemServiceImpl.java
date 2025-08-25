package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.VariableItemRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.mappers.VariableItemMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.VariableItemRepository;
import sn.zeitune.olive_insurance_pricing.app.services.VariableItemService;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class VariableItemServiceImpl extends RetrieveGenericServiceImpl<VariableItem, VariableItemRequestDTO, VariableItemResponseDTO> implements VariableItemService {

    private final VariableItemRepository variableItemRepository;

    public VariableItemServiceImpl (VariableItemRepository variableItemRepository) {
        super(variableItemRepository, VariableItemMapper.getInstance());
        this.variableItemRepository = variableItemRepository;
    }

    @Override
    public VariableItem getEntityByVariableName(String variableName, UUID coverage, PricingType pricingType, UUID managementEntity) {
        return variableItemRepository.findByManagementEntityBeforeAndPricingType_UuidAndCoverageAndVariableName(managementEntity, pricingType.getUuid(), coverage, variableName)
                .orElseThrow(() -> new EntityNotFoundException(VariableItem.class.getSimpleName() + " with name " + variableName + " not found."));
    }
}