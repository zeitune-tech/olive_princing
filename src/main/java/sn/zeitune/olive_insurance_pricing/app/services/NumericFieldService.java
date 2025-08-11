package sn.zeitune.olive_insurance_pricing.app.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.NumericFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;

import java.util.List;
import java.util.UUID;

public interface NumericFieldService {

    NumericFieldResponseDTO create(NumericFieldRequestDTO numericFieldRequestDTO, UUID managementEntity);

    NumericFieldResponseDTO findByUuid(UUID uuid);

    List<NumericFieldResponseDTO> findAll();

    Page<NumericFieldResponseDTO> findAll(Pageable pageable, UUID managementEntity);

    List<NumericFieldResponseDTO> findByProduct(UUID product);

    List<NumericFieldResponseDTO> searchByLabel(String label);

    NumericFieldResponseDTO updateByUuid(UUID uuid, NumericFieldRequestDTO numericFieldRequestDTO);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    NumericField getEntityByUuid(@NotNull(message = "Field UUID is required") UUID uuid);
}
