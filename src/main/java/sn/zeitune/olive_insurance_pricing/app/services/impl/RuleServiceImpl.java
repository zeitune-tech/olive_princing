package sn.zeitune.olive_insurance_pricing.app.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.RuleRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.RuleResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.RuleService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {
    @Override
    public RuleResponseDTO create(RuleRequestDTO ruleRequestDTO) {
        return null;
    }

    @Override
    public RuleResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public RuleResponseDTO findByUuid(UUID uuid) {
        return null;
    }

    @Override
    public List<RuleResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public Page<RuleResponseDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<RuleResponseDTO> findByValue(Double value) {
        return List.of();
    }

    @Override
    public List<RuleResponseDTO> findByProduct(UUID product) {
        return List.of();
    }

    @Override
    public List<RuleResponseDTO> searchByLabel(String label) {
        return List.of();
    }

    @Override
    public RuleResponseDTO update(Long id, RuleRequestDTO ruleRequestDTO) {
        return null;
    }

    @Override
    public RuleResponseDTO updateByUuid(UUID uuid, RuleRequestDTO ruleRequestDTO) {
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
