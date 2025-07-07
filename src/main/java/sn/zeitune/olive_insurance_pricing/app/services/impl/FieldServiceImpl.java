package sn.zeitune.olive_insurance_pricing.app.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.FieldService;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {
    @Override
    public FieldResponseDTO create(FieldRequestDTO fieldRequestDTO) {
        return null;
    }

    @Override
    public FieldResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public FieldResponseDTO findByUuid(UUID uuid) {
        return null;
    }

    @Override
    public List<FieldResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public Page<FieldResponseDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<FieldResponseDTO> findByType(FieldType type) {
        return List.of();
    }

    @Override
    public List<FieldResponseDTO> findByProduct(UUID product) {
        return List.of();
    }

    @Override
    public List<FieldResponseDTO> searchByLabel(String label) {
        return List.of();
    }

    @Override
    public FieldResponseDTO update(Long id, FieldRequestDTO fieldRequestDTO) {
        return null;
    }

    @Override
    public FieldResponseDTO updateByUuid(UUID uuid, FieldRequestDTO fieldRequestDTO) {
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
