package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.NumericFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.List;
import java.util.UUID;

public interface NumericFieldService {

    NumericFieldResponseDTO create(NumericFieldRequestDTO numericFieldRequestDTO);

    NumericFieldResponseDTO findById(Long id);

    NumericFieldResponseDTO findByUuid(UUID uuid);

    List<NumericFieldResponseDTO> findAll();

    Page<NumericFieldResponseDTO> findAll(Pageable pageable);

    List<NumericFieldResponseDTO> findByProduct(UUID product);

    List<NumericFieldResponseDTO> searchByLabel(String label);

    NumericFieldResponseDTO update(Long id, NumericFieldRequestDTO numericFieldRequestDTO);

    NumericFieldResponseDTO updateByUuid(UUID uuid, NumericFieldRequestDTO numericFieldRequestDTO);

    void delete(Long id);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
