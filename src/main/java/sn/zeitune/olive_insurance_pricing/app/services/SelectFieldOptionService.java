package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldOptionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOption;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SelectFieldOptionService {

    SelectFieldOptionResponseDTO create(SelectFieldOptionRequestDTO selectFieldOptionRequestDTO);

    SelectFieldOptionResponseDTO findById(Long id);

    SelectFieldOptionResponseDTO findByUuid(UUID uuid);

    SelectFieldOption getEntityByUuid(UUID uuid);

    List<SelectFieldOptionResponseDTO> findAll();

    Page<SelectFieldOptionResponseDTO> findAll(Pageable pageable);

    Optional<SelectFieldOptionResponseDTO> findByName(String name);

    List<SelectFieldOptionResponseDTO> searchByName(String name);

    SelectFieldOptionResponseDTO update(Long id, SelectFieldOptionRequestDTO selectFieldOptionRequestDTO);

    SelectFieldOptionResponseDTO updateByUuid(UUID uuid, SelectFieldOptionRequestDTO selectFieldOptionRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
