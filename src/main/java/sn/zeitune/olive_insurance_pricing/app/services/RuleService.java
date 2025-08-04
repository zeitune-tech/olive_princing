package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.RuleRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.RuleResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Rule;

import java.util.List;
import java.util.UUID;

public interface RuleService {

    RuleResponseDTO create(RuleRequestDTO ruleRequestDTO);

    RuleResponseDTO findByUuid(UUID uuid);

    List<RuleResponseDTO> findAll();

    Page<RuleResponseDTO> findAll(Pageable pageable);

    RuleResponseDTO updateByUuid(UUID uuid, RuleRequestDTO ruleRequestDTO);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    Rule getEntityByUuid(UUID uuid);
}