package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.mappers.ConstantMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.ConstantRepository;
import sn.zeitune.olive_insurance_pricing.app.services.ConstantService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ConstantServiceImpl implements ConstantService {

    private final ConstantRepository constantRepository;

    @Override
    public ConstantResponseDTO create(ConstantRequestDTO constantRequestDTO) {
        Constant constant = ConstantMapper.toEntity(constantRequestDTO);
        constant = constantRepository.save(constant);
        return ConstantMapper.toResponseDTO(constant);
    }

    @Override
    public ConstantResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public ConstantResponseDTO findByUuid(UUID uuid) {
        Constant constant = constantRepository.findByUuid(uuid).orElse(null);
        if (constant == null) throw new EntityNotFoundException("Constant with uuid " + uuid + " not found");
        return ConstantMapper.map(constant);
    }

    @Override
    public List<ConstantResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public Page<ConstantResponseDTO> findAll(Pageable pageable) {
        return constantRepository.findAll(pageable).map(ConstantMapper::map);
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
