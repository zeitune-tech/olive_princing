package sn.zeitune.olive_insurance_pricing.app.dtos.externals;

import lombok.Builder;
import sn.zeitune.olive_insurance_pricing.enums.ManagementEntityType;

import java.util.UUID;

@Builder
public record ManagementEntityResponse(
        UUID id,
        String name,
        String acronym,
        String email,
        String phone,
        String address,
        String logo,
        String fax,
        String gsm,
        ManagementEntityType type,
        ManagementEntityResponse superiorEntity

) {
}
