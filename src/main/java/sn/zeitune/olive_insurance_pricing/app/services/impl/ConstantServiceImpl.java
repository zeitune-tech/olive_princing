package sn.zeitune.olive_insurance_pricing.app.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.ConstantService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ConstantServiceImpl implements ConstantService {
    @Override
    public ConstantResponseDTO create(ConstantRequestDTO constantRequestDTO) {
        return null;
    }

    @Override
    public ConstantResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public ConstantResponseDTO findByUuid(UUID uuid) {
        return null;
    }

    @Override
    public List<ConstantResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public Page<ConstantResponseDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ConstantResponseDTO> findByValue(Double value) {
        return List.of();
    }

    @Override
    public List<ConstantResponseDTO> findByValueRange(Double minValue, Double maxValue) {
        return List.of();
    }

    @Override
    public List<ConstantResponseDTO> findByProduct(UUID product) {
        return List.of();
    }

    @Override
    public List<ConstantResponseDTO> searchByLabel(String label) {
        return List.of();
    }

    @Override
    public ConstantResponseDTO update(Long id, ConstantRequestDTO constantRequestDTO) {
        return null;
    }

    @Override
    public ConstantResponseDTO updateByUuid(UUID uuid, ConstantRequestDTO constantRequestDTO) {
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
