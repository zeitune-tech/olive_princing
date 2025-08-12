package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.PricingTypeRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.PricingTypeResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;

public class PricingTypeMapper {

    public static PricingType map(PricingTypeRequestDTO dto, PricingType pricingType) {
        pricingType.setName(dto.getName());
        pricingType.setDescription(dto.getDescription());
        pricingType.setProduct(dto.getProduct());
        pricingType.setBranch(dto.getBranch());
        return pricingType;
    }

    public static PricingTypeResponseDTO map(PricingType pricingType) {
        if (pricingType == null) return null;

        PricingTypeResponseDTO pricingTypeResponseDTO = new PricingTypeResponseDTO();
        pricingTypeResponseDTO.setId(pricingType.getUuid());
        pricingTypeResponseDTO.setName(pricingType.getName());
        pricingTypeResponseDTO.setDescription(pricingType.getDescription());
        pricingTypeResponseDTO.setProduct(pricingType.getProduct());
        pricingTypeResponseDTO.setBranch(pricingType.getBranch());
        pricingTypeResponseDTO.setCreatedAt(pricingType.getCreatedAt());
        pricingTypeResponseDTO.setUpdatedAt(pricingType.getUpdatedAt());
        return pricingTypeResponseDTO;
    }
}