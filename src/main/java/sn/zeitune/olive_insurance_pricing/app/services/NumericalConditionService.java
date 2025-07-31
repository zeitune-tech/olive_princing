package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.NumericConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.NumericConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.NumericCondition;

import java.util.List;
import java.util.UUID;

public interface NumericalConditionService {

    NumericConditionResponseDTO create(NumericConditionRequestDTO numericConditionRequestDTO);

    NumericConditionResponseDTO findById(Long id);

    NumericConditionResponseDTO findByUuid(UUID uuid);

    List<NumericConditionResponseDTO> findAll();

    Page<NumericConditionResponseDTO> findAll(Pageable pageable);

    List<NumericConditionResponseDTO> findByField(Long fieldId);

    NumericConditionResponseDTO update(Long id, NumericConditionRequestDTO numericConditionRequestDTO);

    NumericConditionResponseDTO updateByUuid(UUID uuid, NumericConditionRequestDTO numericConditionRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    NumericCondition getEntityByUuid(UUID uuid);
}