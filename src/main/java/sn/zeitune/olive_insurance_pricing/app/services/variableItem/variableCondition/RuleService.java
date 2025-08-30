package sn.zeitune.olive_insurance_pricing.app.services.variableItem.variableCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition.RuleRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition.RuleResponseDTO;

import java.util.List;
import java.util.UUID;

public interface RuleService {
    RuleResponseDTO create(RuleRequestDTO ruleRequestDTO);

    List<RuleResponseDTO> findAll();

    Page<RuleResponseDTO> findAll(Pageable pageable);

    RuleResponseDTO updateByUuid(UUID uuid, RuleRequestDTO ruleRequestDTO);

    void deleteByUuid(UUID uuid);
}