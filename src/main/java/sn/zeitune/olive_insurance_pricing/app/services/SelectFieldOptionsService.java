package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldOptionsRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptions;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SelectFieldOptionsService {

    SelectFieldOptionsResponseDTO create(SelectFieldOptionsRequestDTO selectFieldOptionsRequestDTO);

    SelectFieldOptionsResponseDTO findById(Long id);

    SelectFieldOptionsResponseDTO findByUuid(UUID uuid);

    SelectFieldOptions getEntityByUuid(UUID uuid);

    List<SelectFieldOptionsResponseDTO> findAll();

    Page<SelectFieldOptionsResponseDTO> findAll(Pageable pageable);

    Optional<SelectFieldOptionsResponseDTO> findByName(String name);

    List<SelectFieldOptionsResponseDTO> searchByName(String name);

    SelectFieldOptionsResponseDTO update(Long id, SelectFieldOptionsRequestDTO selectFieldOptionsRequestDTO);

    SelectFieldOptionsResponseDTO updateByUuid(UUID uuid, SelectFieldOptionsRequestDTO selectFieldOptionsRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
