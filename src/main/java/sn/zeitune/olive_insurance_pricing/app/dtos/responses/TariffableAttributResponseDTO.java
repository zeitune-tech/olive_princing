package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sn.zeitune.olive_insurance_pricing.app.dtos.externals.AttributOptionExternalDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.externals.TypeOfAttributExternalDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.externals.ValidationAttributExternalDTO;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.List;

@Builder
@Getter
@Setter
public class TariffableAttributResponseDTO {
    private String controlName;
    private String label;
    private FieldType type;
    private List<AttributOptionResponseDTO> options;
}