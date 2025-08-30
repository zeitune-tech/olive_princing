package sn.zeitune.olive_insurance_pricing.app.services.variableItem.field;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.field.SelectFieldOptionValueRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.SelectFieldOptionValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectFieldOptionValue;

import java.util.UUID;

public interface SelectFieldOptionValueService {

    SelectFieldOptionValueResponseDTO create(SelectFieldOptionValueRequestDTO selectFieldOptionValueRequestDTO, UUID managementEntity);

    Page<SelectFieldOptionValueResponseDTO> findAll(Pageable pageable, UUID managementEntity);

    SelectFieldOptionValueResponseDTO updateByUuid(UUID uuid, SelectFieldOptionValueRequestDTO selectFieldOptionValueRequestDTO);

    void deleteByUuid(UUID uuid);

//    SelectFieldOptionValueResponseDTO findByUuid(UUID uuid);
//    List<SelectFieldOptionValueResponseDTO> findAll();
//    List<SelectFieldOptionValueResponseDTO> searchByLabel(String label);

    boolean existsByUuid(UUID uuid);

    SelectFieldOptionValue getEntityByUuid(UUID uuid);
}
