package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.PricingTypeDetailedResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.FormulaMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.VariableItemMapper;

import java.util.*;
import java.util.stream.Collectors;

public class PricingTypeDetailedMapper {

    private static Map<UUID, List<VariableItemResponseDTO>> getVariableItemMap(List<VariableItem> variableItemList) {
        return variableItemList.stream()
                .filter(variableItem -> (variableItem instanceof NumericField || variableItem instanceof SelectField || variableItem instanceof Constant))
                .map(VariableItemMapper.getInstance()::map)
                .collect(Collectors.groupingBy(VariableItemResponseDTO::getCoverage));
    }

    private static Map<UUID, List<FormulaResponseDTO>> getFormulaMap(List<VariableItem> variableItemList) {
        return variableItemList.stream()
                .filter(variableItem -> variableItem instanceof Formula)
                .map(variableItem -> FormulaMapper.getInstance().map((Formula) variableItem))
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