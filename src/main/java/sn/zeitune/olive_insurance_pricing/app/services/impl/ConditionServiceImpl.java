package sn.zeitune.olive_insurance_pricing.app.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.ConditionService;
import sn.zeitune.olive_insurance_pricing.enums.Operator;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ConditionServiceImpl implements ConditionService {
    @Override
    public ConditionResponseDTO create(ConditionRequestDTO conditionRequestDTO) {
        return null;
    }

    @Override
    public ConditionResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public ConditionResponseDTO findByUuid(UUID uuid) {
        return null;
    }

    @Override
    public List<ConditionResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public Page<ConditionResponseDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ConditionResponseDTO> findByValue(Double value) {
        return List.of();
    }

    @Override
    public List<ConditionResponseDTO> findByOperator(Operator operator) {
        return List.of();
    }

    @Override
    public List<ConditionResponseDTO> findByField(Long fieldId) {
        return List.of();
    }

    @Override
    public ConditionResponseDTO update(Long id, ConditionRequestDTO conditionRequestDTO) {
        return null;
    }

    @Override
    public ConditionResponseDTO updateByUuid(UUID uuid, ConditionRequestDTO conditionRequestDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteByUuid(UUID uuid) {

    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return false;
    }
}
