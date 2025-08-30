package sn.zeitune.olive_insurance_pricing.app.dtos.externals;

import sn.zeitune.olive_insurance_pricing.enums.CalculationMode;
import sn.zeitune.olive_insurance_pricing.enums.CoverageNature;

import java.util.UUID;

public record CoverageExternalDTO(
        UUID id,
        CoverageNature nature,
        boolean isFree,
        boolean isFlatRate,
        CalculationMode calculationMode,
        Long fixedCapital,
        Long minCapital,
        Long maxCapital,
        int order,
        boolean prorata,
        boolean displayPrime,
        boolean generatesCharacteristic,
        ProductExternalDTO product,
        ManagementEntityExternalDTO managementEntity
) {
}
