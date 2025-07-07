package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ConstantService {

    ConstantResponseDTO create(ConstantRequestDTO constantRequestDTO);

    ConstantResponseDTO findById(Long id);

    ConstantResponseDTO findByUuid(UUID uuid);

    List<ConstantResponseDTO> findAll();

    Page<ConstantResponseDTO> findAll(Pageable pageable);

    List<ConstantResponseDTO> findByValue(Double value);

    List<ConstantResponseDTO> findByValueRange(Double minValue, Double maxValue);

    List<ConstantResponseDTO> findByProduct(UUID product);

    List<ConstantResponseDTO> searchByLabel(String label);

    ConstantResponseDTO update(Long id, ConstantRequestDTO constantRequestDTO);

    ConstantResponseDTO updateByUuid(UUID uuid, ConstantRequestDTO constantRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}