package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.List;
import java.util.UUID;

public interface FieldService {

    FieldResponseDTO create(FieldRequestDTO fieldRequestDTO);

    FieldResponseDTO findById(Long id);

    FieldResponseDTO findByUuid(UUID uuid);

    List<FieldResponseDTO> findAll();

    Page<FieldResponseDTO> findAll(Pageable pageable);

    List<FieldResponseDTO> findByType(FieldType type);

    List<FieldResponseDTO> findByProduct(UUID product);

    List<FieldResponseDTO> searchByLabel(String label);

    FieldResponseDTO update(Long id, FieldRequestDTO fieldRequestDTO);

    FieldResponseDTO updateByUuid(UUID uuid, FieldRequestDTO fieldRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
