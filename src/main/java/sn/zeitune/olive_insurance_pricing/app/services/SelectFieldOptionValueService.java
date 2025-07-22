package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldOptionValueRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SelectFieldOptionValueService {

    SelectFieldOptionValueResponseDTO create(SelectFieldOptionValueRequestDTO selectFieldOptionValueRequestDTO);

    SelectFieldOptionValueResponseDTO findById(Long id);

    SelectFieldOptionValueResponseDTO findByUuid(UUID uuid);

    SelectFieldOptionValue getEntityByUuid(UUID uuid);

    List<SelectFieldOptionValueResponseDTO> findAll();

    Page<SelectFieldOptionValueResponseDTO> findAll(Pageable pageable);

    Optional<SelectFieldOptionValueResponseDTO> findByName(String name);

    List<SelectFieldOptionValueResponseDTO> searchByName(String name);

    List<SelectFieldOptionValueResponseDTO> findByGroup(String group);

    List<SelectFieldOptionValueResponseDTO> searchByLabel(String label);

    SelectFieldOptionValueResponseDTO update(Long id, SelectFieldOptionValueRequestDTO selectFieldOptionValueRequestDTO);

    SelectFieldOptionValueResponseDTO updateByUuid(UUID uuid, SelectFieldOptionValueRequestDTO selectFieldOptionValueRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
