package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;

import java.util.List;
import java.util.UUID;

public interface ConstantService extends RetrieveGenericService<Constant, ConstantRequestDTO, ConstantResponseDTO> {

    ConstantResponseDTO create(ConstantRequestDTO constantRequestDTO, UUID managementEntity);
    ConstantResponseDTO update(UUID uuid, ConstantRequestDTO constantRequestDTO, UUID managementEntity);
    void delete(UUID uuid, UUID managementEntity);

}