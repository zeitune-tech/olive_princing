package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.NumericalConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.NumericalConditionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface NumericalConditionService {

    NumericalConditionResponseDTO create(NumericalConditionRequestDTO numericalConditionRequestDTO);

    NumericalConditionResponseDTO findById(Long id);

    NumericalConditionResponseDTO findByUuid(UUID uuid);

    List<NumericalConditionResponseDTO> findAll();

    Page<NumericalConditionResponseDTO> findAll(Pageable pageable);

    List<NumericalConditionResponseDTO> findByValue(Double value);

    List<NumericalConditionResponseDTO> findByField(Long fieldId);

    NumericalConditionResponseDTO update(Long id, NumericalConditionRequestDTO numericalConditionRequestDTO);

    NumericalConditionResponseDTO updateByUuid(UUID uuid, NumericalConditionRequestDTO numericalConditionRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}