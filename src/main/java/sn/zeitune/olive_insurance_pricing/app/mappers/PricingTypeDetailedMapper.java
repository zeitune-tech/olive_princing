package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.PricingTypeRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.PricingTypeDetailedResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.PricingTypeResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.NumericFieldMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldMapper;

import java.util.*;
import java.util.stream.Collectors;

public class PricingTypeDetailedMapper {

    private static Map<UUID, List<VariableItemResponseDTO>> getVariableItemMap(List<VariableItem> variableItemList) {
        return variableItemList.stream()
                .filter(variableItem -> (variableItem instanceof NumericField || variableItem instanceof SelectField || variableItem instanceof Constant))
                .map(VariableItemMapper::map)
                .collect(Collectors.groupingBy(VariableItemResponseDTO::getCoverage));
    }

    private static Map<UUID, List<FormulaResponseDTO>> getFormulaMap(List<VariableItem> variableItemList) {
        return variableItemList.stream()
                .filter(variableItem -> variableItem instanceof Formula)
                .map(variableItem -> FormulaMapper.map((Formula) variableItem))
                .collect(Collectors.groupingBy(FormulaResponseDTO::getCoverage));
    }

    private static List<UUID> getCoverageIds(List<VariableItem> variableItemList) {
        return variableItemList.stream()
                .map(VariableItem::getCoverage)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
    }

    public static PricingTypeDetailedResponseDTO map(PricingType pricingType, List<VariableItem> variableItemList) {
        if (pricingType == null) return null;

        Map<UUID, List<VariableItemResponseDTO>> variableItemMap = getVariableItemMap(variableItemList);
        Map<UUID, List<FormulaResponseDTO>> formulaMap = getFormulaMap(variableItemList);
        List<UUID> coverageIds = getCoverageIds(variableItemList);

        Set<PricingTypeDetailedResponseDTO.CoverageItemResponseDTO> coverages = new HashSet<>();

        for (UUID coverageId : coverageIds) {
            PricingTypeDetailedResponseDTO.CoverageItemResponseDTO coverageItem = new PricingTypeDetailedResponseDTO.CoverageItemResponseDTO();
            coverageItem.setId(coverageId);
            // Set formulas
            coverageItem.setFormulas(new HashSet<>(formulaMap.getOrDefault(coverageId, Collections.emptyList())));
            // Set variables
            coverageItem.setVariables(new HashSet<>(variableItemMap.getOrDefault(coverageId, Collections.emptyList())));
            coverages.add(coverageItem);
        }

        PricingTypeDetailedResponseDTO pricingTypeDetailedResponseDTO = (PricingTypeDetailedResponseDTO) PricingTypeMapper.map(pricingType, true);
        pricingTypeDetailedResponseDTO.setCoverages(coverages);
        return pricingTypeDetailedResponseDTO;
    }
}