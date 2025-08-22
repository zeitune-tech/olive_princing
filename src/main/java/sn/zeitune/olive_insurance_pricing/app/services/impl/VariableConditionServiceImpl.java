package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.VariableConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;
import sn.zeitune.olive_insurance_pricing.app.entities.Rule;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.mappers.VariableConditionMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.VariableConditionRepository;
import sn.zeitune.olive_insurance_pricing.app.services.PricingTypeService;
import sn.zeitune.olive_insurance_pricing.app.services.RuleService;
import sn.zeitune.olive_insurance_pricing.app.services.VariableConditionService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class VariableConditionServiceImpl implements VariableConditionService {

    private final VariableConditionRepository variableConditionRepository;
    private final PricingTypeService pricingTypeService;
    private final RuleService ruleService;

    @Override
    public VariableConditionResponseDTO create(VariableConditionRequestDTO variableConditionDto, UUID managementEntity) {
        // Vérifier si une condition variable avec le même nom de variable existe déjà
        if (variableConditionRepository.existsByVariableName(variableConditionDto.getVariableName()))
            throw new IllegalArgumentException("Une condition variable avec le nom de variable '" + variableConditionDto.getVariableName() + "' existe déjà");

        VariableCondition variableCondition = new VariableCondition();
        VariableConditionMapper.putRequestValue(variableConditionDto, variableCondition);
        for (UUID uuid : variableConditionDto.getRuleIds()) {
            if (ruleService.existsByUuid(uuid))
                variableCondition.getRules().add(ruleService.getEntityByUuid(uuid));
        }
        variableCondition.setManagementEntity(managementEntity);
        variableCondition.setPricingType( pricingTypeService.getEntityById(variableConditionDto.getPricingType()) );
        return VariableConditionMapper.map(variableConditionRepository.save(variableCondition));
    }

    @Override
    public VariableConditionResponseDTO findByUuid(UUID uuid) {
        VariableCondition variableCondition = variableConditionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Condition variable non trouvée avec l'UUID : " + uuid));
        return VariableConditionMapper.map(variableCondition);
    }

    @Override
    public Page<VariableConditionResponseDTO> findAll(Pageable pageable, UUID managementEntity) {
        return variableConditionRepository.findAllByManagementEntity(managementEntity, pageable)
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
    public VariableConditionResponseDTO updateByUuid(UUID uuid, VariableConditionRequestDTO variableConditionDto) {
        VariableCondition existingVariableCondition = variableConditionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Condition variable non trouvée avec l'UUID : " + uuid));
        if (!existingVariableCondition.getVariableName().equals(variableConditionDto.getVariableName()) &&
            variableConditionRepository.existsByVariableName(variableConditionDto.getVariableName())) {
            throw new IllegalArgumentException("Une condition variable avec le nom de variable '" + variableConditionDto.getVariableName() + "' existe déjà");
        }
        VariableConditionMapper.putRequestValue(variableConditionDto, existingVariableCondition);
        // Supprimer les règles qui ne sont plus dans la liste
        Set<UUID> incomingRuleIds = variableConditionDto.getRuleIds();
        existingVariableCondition.getRules().removeIf(r -> !incomingRuleIds.contains(r.getUuid()));
        // Ajouter les nouvelles règles
        for (UUID ruleUuid : variableConditionDto.getRuleIds()) {
            Rule ruleEntity = ruleService.getEntityByUuid(ruleUuid);
            existingVariableCondition.getRules().add(ruleEntity);
        }
        return VariableConditionMapper.map(variableConditionRepository.save(existingVariableCondition));
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
