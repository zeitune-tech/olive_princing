package sn.zeitune.olive_insurance_pricing.app.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.Field;

import java.util.List;
import java.util.UUID;

public interface FieldService {

    FieldResponseDTO findByUuid(UUID uuid, UUID managementEntity);

//    List<FieldResponseDTO> findAll();

    Page<FieldResponseDTO> findAll(Pageable pageable, UUID managementEntity);
//
//    List<FieldResponseDTO> findByProduct(UUID product);
//
//    List<FieldResponseDTO> searchByLabel(String label);
//
//    void deleteByUuid(UUID uuid);
//
//    boolean existsByUuid(UUID uuid);

    Field getEntityByUuid(@NotNull(message = "Field UUID is required") UUID uuid);
}
