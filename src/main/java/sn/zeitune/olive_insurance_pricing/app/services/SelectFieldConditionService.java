package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.SelectFieldConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.SelectFieldConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.Condition;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.SelectFieldCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;

import java.util.List;
import java.util.UUID;

public interface SelectFieldConditionService {

    SelectFieldConditionResponseDTO create(SelectFieldConditionRequestDTO selectFieldConditionRequestDTO);

    SelectFieldConditionResponseDTO findById(Long id);

    SelectFieldConditionResponseDTO findByUuid(UUID uuid);

    List<SelectFieldConditionResponseDTO> findAll();

    Page<SelectFieldConditionResponseDTO> findAll(Pageable pageable);

    List<SelectFieldConditionResponseDTO> findByValue(SelectFieldOptionValue value);

    List<SelectFieldConditionResponseDTO> findByField(Long fieldId);

    SelectFieldConditionResponseDTO update(Long id, SelectFieldConditionRequestDTO selectFieldConditionRequestDTO);

    SelectFieldConditionResponseDTO updateByUuid(UUID uuid, SelectFieldConditionRequestDTO selectFieldConditionRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    SelectFieldCondition getEntityByUuid(UUID uuid);
}