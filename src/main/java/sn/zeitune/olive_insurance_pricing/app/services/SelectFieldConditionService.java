package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.SelectConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.SelectConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.SelectCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;

import java.util.List;
import java.util.UUID;

public interface SelectFieldConditionService {

    SelectConditionResponseDTO create(SelectConditionRequestDTO selectConditionRequestDTO);

    SelectConditionResponseDTO findById(Long id);

    SelectConditionResponseDTO findByUuid(UUID uuid);

    List<SelectConditionResponseDTO> findAll();

    Page<SelectConditionResponseDTO> findAll(Pageable pageable);

    List<SelectConditionResponseDTO> findByValue(SelectFieldOptionValue value);

    List<SelectConditionResponseDTO> findByField(Long fieldId);

    SelectConditionResponseDTO update(Long id, SelectConditionRequestDTO selectConditionRequestDTO);

    SelectConditionResponseDTO updateByUuid(UUID uuid, SelectConditionRequestDTO selectConditionRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    SelectCondition getEntityByUuid(UUID uuid);
}