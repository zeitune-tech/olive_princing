package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.enums.Operator;

import java.util.List;
import java.util.UUID;

public interface ConditionService {

    ConditionResponseDTO create(ConditionRequestDTO conditionRequestDTO);

    ConditionResponseDTO findById(Long id);

    ConditionResponseDTO findByUuid(UUID uuid);

    List<ConditionResponseDTO> findAll();

    Page<ConditionResponseDTO> findAll(Pageable pageable);

    List<ConditionResponseDTO> findByValue(Double value);

    List<ConditionResponseDTO> findByOperator(Operator operator);

    List<ConditionResponseDTO> findByField(Long fieldId);

    ConditionResponseDTO update(Long id, ConditionRequestDTO conditionRequestDTO);

    ConditionResponseDTO updateByUuid(UUID uuid, ConditionRequestDTO conditionRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}