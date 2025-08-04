package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.VariableConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableConditionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface VariableConditionService {

    VariableConditionResponseDTO create(VariableConditionRequestDTO variableConditionDto);

    VariableConditionResponseDTO findByUuid(UUID uuid);

    List<VariableConditionResponseDTO> findAll();

    Page<VariableConditionResponseDTO> findAll(Pageable pageable);

    List<VariableConditionResponseDTO> findByProduct(UUID product);

    List<VariableConditionResponseDTO> searchByLabel(String label);

    VariableConditionResponseDTO updateByUuid(UUID uuid, VariableConditionRequestDTO variableConditionDto);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
