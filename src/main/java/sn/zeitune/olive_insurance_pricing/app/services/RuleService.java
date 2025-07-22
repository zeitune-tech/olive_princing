package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.RuleRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.RuleResponseDTO;

import java.util.List;
import java.util.UUID;

public interface RuleService {

    RuleResponseDTO create(RuleRequestDTO ruleRequestDTO);

    RuleResponseDTO findById(Long id);

    RuleResponseDTO findByUuid(UUID uuid);

    List<RuleResponseDTO> findAll();

    Page<RuleResponseDTO> findAll(Pageable pageable);

    List<RuleResponseDTO> findByValue(Double value);

    RuleResponseDTO update(Long id, RuleRequestDTO ruleRequestDTO);

    RuleResponseDTO updateByUuid(UUID uuid, RuleRequestDTO ruleRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}