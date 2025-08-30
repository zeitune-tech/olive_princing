package sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem.variableCondition;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition.VariableConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition.VariableConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.Rule;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.variableCondition.VariableConditionMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.variableCondition.RuleRepository;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.variableCondition.VariableConditionRepository;
import sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem.RetrieveGenericServiceImpl;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.VariableItemPreparationService;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.variableCondition.VariableConditionService;
import sn.zeitune.olive_insurance_pricing.app.utils.ErrorManager;

import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class VariableConditionServiceImpl extends RetrieveGenericServiceImpl<VariableCondition, VariableConditionRequestDTO, VariableConditionResponseDTO> implements VariableConditionService {

    private final VariableConditionRepository variableConditionRepository;
    private final RuleRepository ruleRepository;
    private final VariableItemPreparationService variableItemPreparationService;
    private final VariableConditionMapper mapper;

    public VariableConditionServiceImpl(VariableConditionRepository variableConditionRepository,
                               RuleRepository ruleRepository,
                               VariableItemPreparationService variableItemPreparationService
    ) {
        super(variableConditionRepository, VariableConditionMapper.getInstance());
        this.variableConditionRepository = variableConditionRepository;
        this.ruleRepository = ruleRepository;
        this.variableItemPreparationService = variableItemPreparationService;
        this.mapper = VariableConditionMapper.getInstance();
    }


    @Override
    public VariableConditionResponseDTO create(VariableConditionRequestDTO variableConditionDto, UUID managementEntity) {
        VariableCondition variableCondition = (VariableCondition) variableItemPreparationService.prepareCreationEntity(variableConditionDto, new VariableCondition(), managementEntity);
        mapper.putRequestValue(variableConditionDto, variableCondition);
        for (UUID uuid : variableConditionDto.getRuleIds())
            variableCondition.getRules().add(ruleRepository.findByUuid(uuid).orElseThrow(() -> ErrorManager.sayEntityNotFound(Rule.class, "uuid: " + uuid)));
        return mapper.map(variableConditionRepository.save(variableCondition));
    }

    @Override
    public VariableConditionResponseDTO update(UUID uuid, VariableConditionRequestDTO variableConditionDto, UUID managementEntity) {
        VariableCondition existingVariableCondition = (VariableCondition) variableItemPreparationService.prepareUpdatingEntity(variableConditionDto, getEntityByUuid(uuid), managementEntity);
        mapper.putRequestValue(variableConditionDto, existingVariableCondition);
        // Supprimer les règles qui ne sont plus dans la liste
        Set<UUID> incomingRuleIds = variableConditionDto.getRuleIds();
        existingVariableCondition.getRules().removeIf(r -> !incomingRuleIds.contains(r.getUuid()));
        // Ajouter les nouvelles règles
        for (UUID ruleUuid : variableConditionDto.getRuleIds()) {
            Rule ruleEntity = ruleRepository.findByUuid(ruleUuid).orElseThrow(() -> ErrorManager.sayEntityNotFound(Rule.class, "uuid: " + ruleUuid));
            existingVariableCondition.getRules().add(ruleEntity);
        }
        return mapper.map(variableConditionRepository.save(existingVariableCondition));
    }

    @Override
    public void delete(UUID uuid, UUID managementEntity) {
        VariableCondition variableCondition = getEntityByUuid(uuid);
        variableConditionRepository.delete(variableCondition);
    }

    VariableCondition getEntityByUuid(UUID uuid) {
        return variableConditionRepository.findByUuidAndDeletedIsFalse(uuid)
                .orElseThrow(() -> ErrorManager.sayEntityNotFound(VariableCondition.class, "uuid: " + uuid));
    }

}
