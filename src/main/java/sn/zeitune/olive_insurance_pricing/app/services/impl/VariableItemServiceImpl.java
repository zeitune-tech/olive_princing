package sn.zeitune.olive_insurance_pricing.app.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public VariableItemResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public VariableItemResponseDTO findByUuid(UUID uuid) {
        return null;
    }

    @Override
    public List<VariableItemResponseDTO> findAll() {
        return variableItemRepository.findAllByOrderByLabelAsc().stream().map(VariableItemMapper::map).toList();
    }

    @Override
    public Page<VariableItemResponseDTO> findAll(Pageable pageable) {
        return null;
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
    public void delete(Long id) {

    }

    @Override
    public void deleteByUuid(UUID uuid) {

    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return false;
    }

    @Override
    public VariableItem getEntityByUuid(UUID uuid) {
        return null;
    }

//    @Override
//    public VariableItem findByVariableName(String variableName) {
//        return variableItemRepository.findByVariableName(variableName)
//                .orElseThrow(() -> new RuntimeException("Variable item not found with name: " + variableName));
//    }
}
