package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;

import java.util.List;
import java.util.UUID;

public interface SelectFieldService {

    SelectFieldResponseDTO create(SelectFieldRequestDTO selectFieldRequestDTO);

    SelectFieldResponseDTO findById(Long id);

    SelectFieldResponseDTO findByUuid(UUID uuid);
    SelectField getEntityByUuid(UUID uuid);

    List<SelectFieldResponseDTO> findAll();

    Page<SelectFieldResponseDTO> findAll(Pageable pageable);

    List<SelectFieldResponseDTO> findByProduct(UUID product);

    List<SelectFieldResponseDTO> searchByLabel(String label);

    SelectFieldResponseDTO update(Long id, SelectFieldRequestDTO selectFieldRequestDTO);

    SelectFieldResponseDTO updateByUuid(UUID uuid, SelectFieldRequestDTO selectFieldRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
