package sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.clients.VehicleSettingsClient;
import sn.zeitune.olive_insurance_pricing.app.dtos.externals.CoverageExternalDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.evaluation.EvaluationRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationRequiredFieldsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationResultResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.*;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.repositories.PricingTypeRepository;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.FormulaRepository;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.VariableItemRepository;
import sn.zeitune.olive_insurance_pricing.app.services.EvaluationService;
import sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.requiredFieldsForVariableItem.GetRequiredFieldsForVariableItem;
import sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.variableEvaluation.numericResult.EvaluateFormula;
import sn.zeitune.olive_insurance_pricing.app.utils.ErrorManager;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final FormulaRepository formulaRepository;
    private final VariableItemRepository variableItemRepository;
    private final PricingTypeRepository pricingTypeRepository;
    private final VehicleSettingsClient vehicleSettingsClient;

    @Override
    public EvaluationRequiredFieldsResponseDTO getRequiredFields(UUID id) {
        EvaluationRequiredFieldsResponseDTO dto = new EvaluationRequiredFieldsResponseDTO();
        Formula formula = getFormula(id);
        if (formula.getExpression() != null) {
            Matcher matcher = Pattern.compile("\\{\\{(.*?)}}").matcher(formula.getExpression());
            while (matcher.find()) {
                String variableName = matcher.group(1).trim();
                GetRequiredFieldsForVariableItem getRequiredFieldsForVariableItem = GetRequiredFieldsForVariableItem.create(variableItemRepository.findByVariableNameAndManagementEntityAndPricingType_UuidAndCoverageAndDeletedIsFalse(variableName, formula.getManagementEntity(), formula.getPricingType().getUuid(), formula.getCoverage()).orElseThrow(() -> ErrorManager.sayEntityNotFound(VariableItem.class, "variable name: " + variableName)));
                dto.addFieldList(getRequiredFieldsForVariableItem.execute());
            }
        }
        return dto;
    }

    @Override
    public List<EvaluationResultResponseDTO> doEvaluation(UUID pricingTypeId, EvaluationRequestDTO data, UUID managementEntity) {
        List<EvaluationResultResponseDTO> result = new ArrayList<>();
        for (Map.Entry<UUID, Formula> entry : getEffectiveFormulasByCoverageOfPricingType(pricingTypeId).entrySet()) {
            if (entry.getValue() == null) {
                result.add(
                        EvaluationResultResponseDTO.builder()
                                .coverage(entry.getKey())
                                .value(0.0)
                                .build()
                );
                continue;
            }
            EvaluateFormula evaluateFormula = new EvaluateFormula(entry.getValue(), data.getFields());
            result.add(
                    EvaluationResultResponseDTO.builder()
                            .coverage(entry.getKey())
                            .value(evaluateFormula.execute())
                            .build()
            );
            System.err.println("Final expression to evaluate: " + entry.getValue().getExpression() + " => " + evaluateFormula.execute());
        }
        return result;
    }

    Formula getFormula(UUID id) {
        return formulaRepository.findByUuidAndDeletedIsFalse(id)
                .orElseThrow(() -> ErrorManager.sayEntityNotFound(Formula.class, "id: " + id));
    }

    PricingType getPricingType(UUID id) {
        return pricingTypeRepository.findByUuidAndDeletedIsFalse(id)
                .orElseThrow(() -> ErrorManager.sayEntityNotFound(PricingType.class, "id: " + id));
    }

    Optional<Formula> getEffectiveFormulaByCoverageOfPricingType(PricingType pricingType, UUID coverageId) {
        return formulaRepository.findAllByPricingTypeAndCoverageAndDeletedIsFalseAndDateEffectiveLessThanEqualOrderByDateEffectiveDescUpdatedAtDesc(pricingType, coverageId, LocalDate.now())
                .stream().findFirst();
    }

    Map<UUID, Formula> getEffectiveFormulasByCoverageOfPricingType(UUID id) {
        PricingType pricingType = getPricingType(id);
        Map<UUID, Formula> formulas = new HashMap<>();
        List<CoverageExternalDTO> coverageExternalDTOList = vehicleSettingsClient.getCoveragesByProduct(pricingType.getProduct(), pricingType.getManagementEntity());
        for (CoverageExternalDTO coverageExternalDTO : coverageExternalDTOList) {
            formulas.put(coverageExternalDTO.id(),
                    getEffectiveFormulaByCoverageOfPricingType(pricingType, coverageExternalDTO.id())
                            .orElse(null)
            );
        }
        return formulas;
    }

}