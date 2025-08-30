package sn.zeitune.olive_insurance_pricing.app.services.variableItem.variableCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition.NumericConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition.NumericConditionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface NumericalConditionService {

    NumericConditionResponseDTO create(NumericConditionRequestDTO numericConditionRequestDTO);

    NumericConditionResponseDTO findByUuid(UUID uuid);

    List<NumericConditionResponseDTO> findAll();

    Page<NumericConditionResponseDTO> findAll(Pageable pageable);

    NumericConditionResponseDTO updateByUuid(UUID uuid, NumericConditionRequestDTO numericConditionRequestDTO);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}