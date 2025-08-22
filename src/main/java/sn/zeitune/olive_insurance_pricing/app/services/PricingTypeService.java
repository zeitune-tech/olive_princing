package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.PricingTypeRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.PricingTypeResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PricingTypeService {
    PricingTypeResponseDTO create(PricingTypeRequestDTO request);
    PricingTypeResponseDTO update(UUID id, PricingTypeRequestDTO request);
    void delete(UUID id);
    PricingTypeResponseDTO getById(UUID id);
    PricingTypeResponseDTO getDetailedById(UUID id);
    List<PricingTypeResponseDTO> getByProduct(UUID productId);
    Page<PricingTypeResponseDTO> getAll(Pageable pageable);
    PricingType getEntityById(UUID id);

}
