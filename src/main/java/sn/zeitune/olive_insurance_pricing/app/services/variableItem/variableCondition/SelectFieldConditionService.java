package sn.zeitune.olive_insurance_pricing.app.services.variableItem.variableCondition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition.SelectConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition.SelectConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectFieldOptionValue;

import java.util.List;
import java.util.UUID;

public interface SelectFieldConditionService {

    SelectConditionResponseDTO create(SelectConditionRequestDTO selectConditionRequestDTO);

    SelectConditionResponseDTO findByUuid(UUID uuid);

    List<SelectConditionResponseDTO> findAll();

    Page<SelectConditionResponseDTO> findAll(Pageable pageable);

    List<SelectConditionResponseDTO> findByValue(SelectFieldOptionValue value);

    SelectConditionResponseDTO updateByUuid(UUID uuid, SelectConditionRequestDTO selectConditionRequestDTO);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

}