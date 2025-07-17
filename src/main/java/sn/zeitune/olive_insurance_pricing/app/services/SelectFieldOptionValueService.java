package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FieldPossibilitiesValueRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FieldPossibilitiesValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.SelectFieldOptionValue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FieldPossibilitiesValueService {

    FieldPossibilitiesValueResponseDTO create(FieldPossibilitiesValueRequestDTO fieldPossibilitiesValueRequestDTO);

    FieldPossibilitiesValueResponseDTO findById(Long id);

    FieldPossibilitiesValueResponseDTO findByUuid(UUID uuid);

    SelectFieldOptionValue getEntityByUuid(UUID uuid);

    List<FieldPossibilitiesValueResponseDTO> findAll();

    Page<FieldPossibilitiesValueResponseDTO> findAll(Pageable pageable);

    Optional<FieldPossibilitiesValueResponseDTO> findByName(String name);

    List<FieldPossibilitiesValueResponseDTO> searchByName(String name);

    List<FieldPossibilitiesValueResponseDTO> findByGroup(String group);

    List<FieldPossibilitiesValueResponseDTO> searchByLabel(String label);

    FieldPossibilitiesValueResponseDTO update(Long id, FieldPossibilitiesValueRequestDTO fieldPossibilitiesValueRequestDTO);

    FieldPossibilitiesValueResponseDTO updateByUuid(UUID uuid, FieldPossibilitiesValueRequestDTO fieldPossibilitiesValueRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
