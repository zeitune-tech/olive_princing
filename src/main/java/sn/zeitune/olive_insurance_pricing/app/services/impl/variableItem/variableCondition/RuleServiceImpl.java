package sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem.variableCondition;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition.RuleRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition.RuleResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.BaseEntity;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.Rule;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.condition.NumericCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.condition.SelectCondition;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.variableCondition.RuleMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.variableCondition.RuleRepository;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.variableCondition.NumericConditionRepository;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.variableCondition.SelectConditionRepository;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.variableCondition.RuleService;
import sn.zeitune.olive_insurance_pricing.app.utils.ErrorManager;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {

    private final RuleRepository ruleRepository;
    private final SelectConditionRepository selectConditionRepository;
    private final NumericConditionRepository numericConditionRepository;

    @Override
    public RuleResponseDTO create(RuleRequestDTO ruleRequestDTO) {
        if (ruleRepository.existsByName(ruleRequestDTO.getName()))
            throw new RuntimeException("Une règle avec le même nom existe déjà : " + ruleRequestDTO.getName());

        Rule rule = RuleMapper.map(ruleRequestDTO, new Rule());
        for (UUID uuid : ruleRequestDTO.getConditions()) {
            if (selectConditionRepository.existsByUuid(uuid)) {
                rule.getConditions().add(selectConditionRepository.findByUuid(uuid).orElseThrow(() -> ErrorManager.sayEntityNotFound(SelectCondition.class, "id: " + uuid)));
            } else if (numericConditionRepository.existsByUuid(uuid)) {
                rule.getConditions().add(numericConditionRepository.findByUuid(uuid).orElseThrow(() -> ErrorManager.sayEntityNotFound(NumericCondition.class, "id: " + uuid)));
            } else {
                throw new EntityNotFoundException("Condition non trouvée avec l'UUID : " + uuid);
            }
        }
        return RuleMapper.map(ruleRepository.save(rule));
    }

    @Override
    public List<RuleResponseDTO> findAll() {
        return ruleRepository.findAll()
                .stream()
                .map(RuleMapper::map)
                .toList();
    }

    @Override
    public Page<RuleResponseDTO> findAll(Pageable pageable) {
        return ruleRepository.findAll(pageable)
                .map(RuleMapper::map);
    }

    @Override
    public RuleResponseDTO updateByUuid(UUID uuid, RuleRequestDTO ruleRequestDTO) {
        Rule existingRule = ruleRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Règle non trouvée avec l'UUID : " + uuid));

        List<UUID> existingConditionUuids = existingRule.getConditions()
                .stream()
                .map(BaseEntity::getUuid)
                .toList();

        for (UUID conditionUuid : existingConditionUuids) {
            if (!ruleRequestDTO.getConditions().contains(conditionUuid)) {
                // Remove condition if it is not in the new request
                existingRule.getConditions().removeIf(condition -> condition.getUuid().equals(conditionUuid));
            }
        }

        for (UUID conditionUuid : ruleRequestDTO.getConditions()) {
            if (existingConditionUuids.contains(conditionUuid))
                continue; // Condition already exists, skip adding it again
            if (selectConditionRepository.existsByUuid(conditionUuid)) {
                existingRule.getConditions().add(selectConditionRepository.findByUuid(conditionUuid).orElseThrow(() -> ErrorManager.sayEntityNotFound(SelectCondition.class, "id: " + conditionUuid)));
            } else if (numericConditionRepository.existsByUuid(conditionUuid)) {
                existingRule.getConditions().add(numericConditionRepository.findByUuid(conditionUuid).orElseThrow(() -> ErrorManager.sayEntityNotFound(NumericCondition.class, "id: " + conditionUuid)));
            } else {
                throw new EntityNotFoundException("Condition non trouvée avec l'UUID : " + conditionUuid);
            }
        }

        RuleMapper.map(ruleRequestDTO, existingRule);
        Rule updatedRule = ruleRepository.save(existingRule);
        return RuleMapper.map(updatedRule);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        Rule rule = ruleRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Règle non trouvée avec l'UUID : " + uuid));
        ruleRepository.delete(rule);
    }

}
