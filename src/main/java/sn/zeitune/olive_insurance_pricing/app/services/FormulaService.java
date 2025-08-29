package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FormulaRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;

import java.util.List;
import java.util.UUID;

public interface FormulaService {

    FormulaResponseDTO create(FormulaRequestDTO formulaRequestDTO, UUID managementEntity);

    FormulaResponseDTO findByUuid(UUID uuid);

    List<FormulaResponseDTO> findAll();

    Page<FormulaResponseDTO> findAll(Pageable pageable);

    List<FormulaResponseDTO> findByProduct(UUID product);

    List<FormulaResponseDTO> searchByLabel(String label);

    List<FormulaResponseDTO> searchByExpression(String expression);

    FormulaResponseDTO updateByUuid(UUID uuid, FormulaRequestDTO formulaRequestDTO, UUID managementEntity);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    Formula getEntityByUuid(UUID uuid);
    Formula getAllEffectiveEntityByPricingType(PricingType pricingType, UUID managementEntity);
}
