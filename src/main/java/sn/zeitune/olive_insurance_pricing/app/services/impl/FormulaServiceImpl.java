package sn.zeitune.olive_insurance_pricing.app.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FormulaRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.FormulaService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FormulaServiceImpl implements FormulaService {

    @Override
    public FormulaResponseDTO create(FormulaRequestDTO formulaRequestDTO) {
        return null;
    }

    @Override
    public FormulaResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public FormulaResponseDTO findByUuid(UUID uuid) {
        return null;
    }

    @Override
    public List<FormulaResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public Page<FormulaResponseDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<FormulaResponseDTO> findByProduct(UUID product) {
        return List.of();
    }

    @Override
    public List<FormulaResponseDTO> searchByLabel(String label) {
        return List.of();
    }

    @Override
    public List<FormulaResponseDTO> searchByExpression(String expression) {
        return List.of();
    }

    @Override
    public FormulaResponseDTO update(Long id, FormulaRequestDTO formulaRequestDTO) {
        return null;
    }

    @Override
    public FormulaResponseDTO updateByUuid(UUID uuid, FormulaRequestDTO formulaRequestDTO) {
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
