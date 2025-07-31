package sn.zeitune.olive_insurance_pricing.app.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.Field;

import java.util.List;
import java.util.UUID;

public interface FieldService {

    FieldResponseDTO findById(Long id);

    FieldResponseDTO findByUuid(UUID uuid);

    List<FieldResponseDTO> findAll();

    Page<FieldResponseDTO> findAll(Pageable pageable);

    List<FieldResponseDTO> findByProduct(UUID product);

    List<FieldResponseDTO> searchByLabel(String label);

//    FieldResponseDTO update(Long id, FieldRequestDTO numericFieldRequestDTO);

//    FieldResponseDTO updateByUuid(UUID uuid, FieldRequestDTO numericFieldRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    Field getEntityByUuid(@NotNull(message = "Field UUID is required") UUID uuid);
}
