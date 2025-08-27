package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class TariffableObjectResponseDTO {
    private Set<TariffableAttributResponseDTO> attributs ;
    private String name;
}