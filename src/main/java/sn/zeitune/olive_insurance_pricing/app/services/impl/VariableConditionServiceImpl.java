package sn.zeitune.olive_insurance_pricing.app.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.VariableConditionDto;
import sn.zeitune.olive_insurance_pricing.app.services.VariableConditionService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class VariableConditionServiceImpl implements VariableConditionService {
    @Override
    public VariableConditionDto create(VariableConditionDto variableConditionDto) {
        return null;
    }

    @Override
    public VariableConditionDto findById(Long id) {
        return null;
    }

    @Override
    public VariableConditionDto findByUuid(UUID uuid) {
        return null;
    }

    @Override
    public List<VariableConditionDto> findAll() {
        return List.of();
    }

    @Override
    public Page<VariableConditionDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<VariableConditionDto> findByProduct(UUID product) {
        return List.of();
    }

    @Override
    public List<VariableConditionDto> searchByLabel(String label) {
        return List.of();
    }

    @Override
    public VariableConditionDto update(Long id, VariableConditionDto variableConditionDto) {
        return null;
    }

    @Override
    public VariableConditionDto updateByUuid(UUID uuid, VariableConditionDto variableConditionDto) {
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
