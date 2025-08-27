package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AttributOptionResponseDTO {
    private String value;
    private String label;
    private boolean isActive;
}
