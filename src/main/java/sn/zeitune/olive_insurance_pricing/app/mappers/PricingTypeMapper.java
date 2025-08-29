package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.PricingTypeRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.PricingTypeDetailedResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.PricingTypeResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;

public class PricingTypeMapper {

    public static void putRequestValue(PricingTypeRequestDTO dto, PricingType pricingType) {
        if (pricingType == null || dto == null) return;
        pricingType.setName(dto.getName());
        pricingType.setDescription(dto.getDescription());
        pricingType.setProduct(dto.getProduct());
        pricingType.setDateEffective(dto.getDateEffective());
        pricingType.setBranch(dto.getBranch());
    }

    public static PricingTypeResponseDTO map(PricingType pricingType, boolean detailed) {
        if (pricingType == null) return null;
        PricingTypeResponseDTO pricingTypeResponseDTO;
        if (detailed) {
            pricingTypeResponseDTO = new PricingTypeDetailedResponseDTO();
        }else {
            pricingTypeResponseDTO = new PricingTypeResponseDTO();
        }
        pricingTypeResponseDTO.setId(pricingType.getUuid());
        pricingTypeResponseDTO.setName(pricingType.getName());
        pricingTypeResponseDTO.setDescription(pricingType.getDescription());
        pricingTypeResponseDTO.setProduct(pricingType.getProduct());
        pricingTypeResponseDTO.setBranch(pricingType.getBranch());
        pricingTypeResponseDTO.setDateEffective(pricingType.getDateEffective());
        pricingTypeResponseDTO.setEffective(pricingType.isEffective());
        pricingTypeResponseDTO.setCreatedAt(pricingType.getCreatedAt());
        pricingTypeResponseDTO.setUpdatedAt(pricingType.getUpdatedAt());
        return pricingTypeResponseDTO;
    }


}