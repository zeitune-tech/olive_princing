package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FieldValueRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FieldValueResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FieldValueService {

    FieldValueResponseDTO create(FieldValueRequestDTO fieldValueRequestDTO);

    FieldValueResponseDTO findById(Long id);

    FieldValueResponseDTO findByUuid(UUID uuid);

    List<FieldValueResponseDTO> findAll();

    Page<FieldValueResponseDTO> findAll(Pageable pageable);

    Optional<FieldValueResponseDTO> findByName(String name);

    List<FieldValueResponseDTO> searchByName(String name);

    FieldValueResponseDTO update(Long id, FieldValueRequestDTO fieldValueRequestDTO);

    FieldValueResponseDTO updateByUuid(UUID uuid, FieldValueRequestDTO fieldValueRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
