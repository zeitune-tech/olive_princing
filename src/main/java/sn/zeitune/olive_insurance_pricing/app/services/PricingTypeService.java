package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.PricingTypeRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.PricingTypeResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;

import java.util.List;
import java.util.UUID;

public interface PricingTypeService {
    PricingTypeResponseDTO create(PricingTypeRequestDTO request, UUID managementEntity);
    PricingTypeResponseDTO update(UUID id, PricingTypeRequestDTO request, UUID managementEntity);
    void delete(UUID id, UUID managementEntity);
    PricingTypeResponseDTO getById(UUID id, UUID managementEntity);
    PricingTypeResponseDTO getDetailedById(UUID id, UUID managementEntity);
    List<PricingTypeResponseDTO> getAllActiveByProduct(UUID productId, UUID managementEntity);
    PricingTypeResponseDTO getEffectivePricingTypeForProduct(UUID productId, UUID managementEntity);

    Page<PricingTypeResponseDTO> getAllActive(Pageable pageable, UUID managementEntity);
    PricingType getEntityById(UUID id, UUID managementEntity);

}
