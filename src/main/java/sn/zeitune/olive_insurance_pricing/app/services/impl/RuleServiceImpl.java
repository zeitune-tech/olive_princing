package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.RuleRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.RuleResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.BaseEntity;
import sn.zeitune.olive_insurance_pricing.app.entities.Rule;
import sn.zeitune.olive_insurance_pricing.app.mappers.RuleMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.RuleRepository;
import sn.zeitune.olive_insurance_pricing.app.services.NumericalConditionService;
import sn.zeitune.olive_insurance_pricing.app.services.RuleService;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldConditionService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {

    private final RuleRepository ruleRepository;
    private final SelectFieldConditionService selectFieldConditionService;
    private final NumericalConditionService numericalConditionService;

    @Override
    public RuleResponseDTO create(RuleRequestDTO ruleRequestDTO) {
        if (ruleRepository.existsByName(ruleRequestDTO.getName()))
            throw new RuntimeException("Une règle avec le même nom existe déjà : " + ruleRequestDTO.getName());

        Rule rule = RuleMapper.map(ruleRequestDTO, new Rule());
        for (UUID uuid : ruleRequestDTO.getConditions()) {
            if (selectFieldConditionService.existsByUuid(uuid)) {
                rule.getConditions().add(selectFieldConditionService.getEntityByUuid(uuid));
            } else if (numericalConditionService.existsByUuid(uuid)) {
                rule.getConditions().add(numericalConditionService.getEntityByUuid(uuid));
            } else {
                throw new EntityNotFoundException("Condition non trouvée avec l'UUID : " + uuid);
            }
        }
        return RuleMapper.map(ruleRepository.save(rule));
    }

    @Override
    public RuleResponseDTO findByUuid(UUID uuid) {
        return RuleMapper.map(getEntityByUuid(uuid));
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

        for (UUID conditionUuid : ruleRequestDTO.getConditions()) {
            if (existingConditionUuids.contains(conditionUuid))
                continue; // Condition already exists, skip adding it again

            if (selectFieldConditionService.existsByUuid(conditionUuid)) {
                existingRule.getConditions().add(selectFieldConditionService.getEntityByUuid(conditionUuid));
            } else if (numericalConditionService.existsByUuid(conditionUuid)) {
                existingRule.getConditions().add(numericalConditionService.getEntityByUuid(conditionUuid));
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

    @Override
    public boolean existsByUuid(UUID uuid) {
        return ruleRepository.existsByUuid(uuid);
    }

    @Override
    public Rule getEntityByUuid(UUID uuid) {
        return ruleRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Règle non trouvée avec l'UUID : " + uuid));

    }
}
