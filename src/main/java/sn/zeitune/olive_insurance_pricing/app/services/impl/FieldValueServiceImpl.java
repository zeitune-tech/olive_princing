package sn.zeitune.olive_insurance_pricing.app.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FieldValueRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FieldValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.FieldValueService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldValueServiceImpl implements FieldValueService {
    @Override
    public FieldValueResponseDTO create(FieldValueRequestDTO fieldValueRequestDTO) {
        return null;
    }

    @Override
    public FieldValueResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public FieldValueResponseDTO findByUuid(UUID uuid) {
        return null;
    }

    @Override
    public List<FieldValueResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public Page<FieldValueResponseDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<FieldValueResponseDTO> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<FieldValueResponseDTO> searchByName(String name) {
        return List.of();
    }

    @Override
    public FieldValueResponseDTO update(Long id, FieldValueRequestDTO fieldValueRequestDTO) {
        return null;
    }

    @Override
    public FieldValueResponseDTO updateByUuid(UUID uuid, FieldValueRequestDTO fieldValueRequestDTO) {
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
