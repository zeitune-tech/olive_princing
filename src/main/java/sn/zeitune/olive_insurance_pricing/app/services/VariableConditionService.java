package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.VariableConditionDto;

import java.util.List;
import java.util.UUID;

public interface VariableConditionService {

    VariableConditionDto create(VariableConditionDto variableConditionDto);

    VariableConditionDto findById(Long id);

    VariableConditionDto findByUuid(UUID uuid);

    List<VariableConditionDto> findAll();

    Page<VariableConditionDto> findAll(Pageable pageable);

    List<VariableConditionDto> findByProduct(UUID product);

    List<VariableConditionDto> searchByLabel(String label);

    VariableConditionDto update(Long id, VariableConditionDto variableConditionDto);

    VariableConditionDto updateByUuid(UUID uuid, VariableConditionDto variableConditionDto);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
