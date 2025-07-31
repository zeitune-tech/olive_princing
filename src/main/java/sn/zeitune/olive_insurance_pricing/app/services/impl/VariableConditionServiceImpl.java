package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.VariableConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.mappers.VariableConditionMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.VariableConditionRepository;
import sn.zeitune.olive_insurance_pricing.app.services.RuleService;
import sn.zeitune.olive_insurance_pricing.app.services.VariableConditionService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class VariableConditionServiceImpl implements VariableConditionService {

    private final VariableConditionRepository variableConditionRepository;
    private final RuleService ruleService;

    @Override
    public VariableConditionResponseDTO create(VariableConditionRequestDTO variableConditionDto) {
        // Vérifier si une condition variable avec le même nom de variable existe déjà
        if (variableConditionRepository.existsByVariableName(variableConditionDto.getVariableName()))
            throw new IllegalArgumentException("Une condition variable avec le nom de variable '" + variableConditionDto.getVariableName() + "' existe déjà");

        
        VariableCondition variableCondition = VariableConditionMapper.map(variableConditionDto);

        for (UUID uuid : variableConditionDto.getRuleIds()) {
            if (ruleService.existsByUuid(uuid))
                variableCondition.getRules().add(ruleService.getEntityByUuid(uuid));
        }

        return VariableConditionMapper.map(variableConditionRepository.save(variableCondition));
    }

    @Override
    public VariableConditionResponseDTO findById(Long id) {
        VariableCondition variableCondition = variableConditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Condition variable non trouvée avec l'ID : " + id));
        return VariableConditionMapper.map(variableCondition);
    }

    @Override
    public VariableConditionResponseDTO findByUuid(UUID uuid) {
        VariableCondition variableCondition = variableConditionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Condition variable non trouvée avec l'UUID : " + uuid));
        return VariableConditionMapper.map(variableCondition);
    }

    @Override
    public List<VariableConditionResponseDTO> findAll() {
        return variableConditionRepository.findAll()
                .stream()
                .map(VariableConditionMapper::map)
                .toList();
    }

    @Override
    public Page<VariableConditionResponseDTO> findAll(Pageable pageable) {
        return variableConditionRepository.findAll(pageable)
                .map(VariableConditionMapper::map);
    }

    @Override
    public List<VariableConditionResponseDTO> findByProduct(UUID product) {
        return variableConditionRepository.findByProduct(product)
                .stream()
                .map(VariableConditionMapper::map)
                .toList();
    }

    @Override
    public List<VariableConditionResponseDTO> searchByLabel(String label) {
        return variableConditionRepository.findByLabelContainingIgnoreCase(label)
                .stream()
                .map(VariableConditionMapper::map)
                .toList();
    }

    @Override
    public VariableConditionResponseDTO update(Long id, VariableConditionRequestDTO variableConditionDto) {
        VariableCondition existingVariableCondition = variableConditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Condition variable non trouvée avec l'ID : " + id));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est la même condition variable)
        if (!existingVariableCondition.getVariableName().equals(variableConditionDto.getVariableName()) &&
            variableConditionRepository.existsByVariableName(variableConditionDto.getVariableName())) {
            throw new IllegalArgumentException("Une condition variable avec le nom de variable '" + variableConditionDto.getVariableName() + "' existe déjà");
        }

        update(existingVariableCondition, variableConditionDto);
        VariableCondition updatedVariableCondition = variableConditionRepository.save(existingVariableCondition);
        return VariableConditionMapper.map(updatedVariableCondition);
    }

    @Override
    public VariableConditionResponseDTO updateByUuid(UUID uuid, VariableConditionRequestDTO variableConditionDto) {
        VariableCondition existingVariableCondition = variableConditionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Condition variable non trouvée avec l'UUID : " + uuid));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est la même condition variable)
        if (!existingVariableCondition.getVariableName().equals(variableConditionDto.getVariableName()) &&
            variableConditionRepository.existsByVariableName(variableConditionDto.getVariableName())) {
            throw new IllegalArgumentException("Une condition variable avec le nom de variable '" + variableConditionDto.getVariableName() + "' existe déjà");
        }
        
        update(existingVariableCondition, variableConditionDto);
        VariableCondition updatedVariableCondition = variableConditionRepository.save(existingVariableCondition);
        return VariableConditionMapper.map(updatedVariableCondition);
    }

    private void update (VariableCondition variableCondition, VariableConditionRequestDTO variableConditionRequestDTO) {
        if (variableCondition == null) return;
        variableCondition.setLabel(variableConditionRequestDTO.getLabel());
        variableCondition.setDescription(variableConditionRequestDTO.getDescription());
        variableCondition.setVariableName(variableConditionRequestDTO.getVariableName());
        variableCondition.setToReturn(variableConditionRequestDTO.getToReturn());
        variableCondition.setManagementEntity(variableConditionRequestDTO.getManagementEntity() != null ? variableConditionRequestDTO.getManagementEntity() : null);
        variableCondition.setProduct(variableConditionRequestDTO.getProduct());
        variableCondition.setBranch(variableConditionRequestDTO.getBranch());

    }

    @Override
    public void delete(Long id) {
        if (!variableConditionRepository.existsById(id)) {
            throw new EntityNotFoundException("Condition variable non trouvée avec l'ID : " + id);
        }
        variableConditionRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        VariableCondition variableCondition = variableConditionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Condition variable non trouvée avec l'UUID : " + uuid));
        variableConditionRepository.delete(variableCondition);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return variableConditionRepository.existsByUuid(uuid);
    }
}
