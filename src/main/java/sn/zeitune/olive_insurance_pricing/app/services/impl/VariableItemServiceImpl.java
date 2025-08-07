package sn.zeitune.olive_insurance_pricing.app.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.mappers.VariableItemMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.VariableItemRepository;
import sn.zeitune.olive_insurance_pricing.app.services.VariableItemService;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
@RequiredArgsConstructor
public class VariableItemServiceImpl implements VariableItemService {

    private final VariableItemRepository variableItemRepository;

    @Override
    public VariableItemResponseDTO findByUuid(UUID uuid) {
        VariableItem variableItem = variableItemRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Variable item not found"));
        return VariableItemMapper.map(variableItem);
    }

    @Override
    public List<VariableItemResponseDTO> findByProduct(UUID product) {
        return List.of();
    }

    @Override
    public List<VariableItemResponseDTO> searchByLabel(String label) {
        return List.of();
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return variableItemRepository.existsByUuid(uuid);
    }

    @Override
    public VariableItem getEntityByUuid(UUID uuid) {
        return variableItemRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Variable item not found with UUID: " + uuid));
    }

    @Override
    public VariableItem findByVariableName(String variable) {
        return variableItemRepository.findByVariableName(variable).orElseThrow(() -> new RuntimeException("Variable not found"));
    }

    @Override
    public List<VariableItemResponseDTO> findAll(UUID managementEntity) {
        return variableItemRepository.findAll()
                .stream()
                .map(VariableItemMapper::map)
                .toList();
    }

}