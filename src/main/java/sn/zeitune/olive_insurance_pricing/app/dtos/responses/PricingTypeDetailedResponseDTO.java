package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.VariableItemResponseDTO;

import java.util.Set;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PricingTypeDetailedResponseDTO extends PricingTypeResponseDTO {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CoverageItemResponseDTO {
        UUID id;
        Set<FormulaResponseDTO> formulas;
        Set<VariableItemResponseDTO> variables;
    }

    Set<CoverageItemResponseDTO> coverages;

}
