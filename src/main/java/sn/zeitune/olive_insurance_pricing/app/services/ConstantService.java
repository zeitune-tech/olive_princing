package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ConstantService {

    ConstantResponseDTO create(ConstantRequestDTO constantRequestDTO, UUID managementEntity);

    ConstantResponseDTO findByUuid(UUID uuid);

    List<ConstantResponseDTO> findAll(UUID managementEntity);

    Page<ConstantResponseDTO> findAll(Pageable pageable, UUID managementEntity);

    List<ConstantResponseDTO> findByValue(Double value);

    List<ConstantResponseDTO> findByProduct(UUID product);

    List<ConstantResponseDTO> searchByLabel(String label);

    ConstantResponseDTO updateByUuid(UUID uuid, ConstantRequestDTO constantRequestDTO);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}