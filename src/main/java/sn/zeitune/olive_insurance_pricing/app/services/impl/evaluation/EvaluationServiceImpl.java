package sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation;

import lombok.RequiredArgsConstructor;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.evaluation.EvaluationRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationRequiredFieldsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationResultResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.*;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.Condition;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.NumericCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.SelectCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldOptionValueMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.PricingTypeRepository;
import sn.zeitune.olive_insurance_pricing.app.services.EvaluationService;
import sn.zeitune.olive_insurance_pricing.app.services.FormulaService;
import sn.zeitune.olive_insurance_pricing.app.services.VariableItemService;
import sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.requiredFieldsForVariableItem.GetRequiredFieldsForVariableItem;
import sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.variableEvaluation.numericResult.EvaluateFormula;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final FormulaService formulaService;
    private final VariableItemService variableItemService;
    private final PricingTypeRepository pricingTypeRepository;

    @Override
    public EvaluationRequiredFieldsResponseDTO getRequiredFields(UUID id) {
        EvaluationRequiredFieldsResponseDTO dto = new EvaluationRequiredFieldsResponseDTO();
        Formula formula = formulaService.getEntityByUuid(id);
        if (formula.getExpression() != null) {
            Matcher matcher = Pattern.compile("\\{\\{(.*?)}}").matcher(formula.getExpression());
            while (matcher.find()) {
                String variableName = matcher.group(1).trim();
                GetRequiredFieldsForVariableItem getRequiredFieldsForVariableItem = GetRequiredFieldsForVariableItem.create(variableItemService.getEntityByVariableName(variableName, formula.getCoverage(), formula.getPricingType(), formula.getManagementEntity()));
                dto.addFieldList(getRequiredFieldsForVariableItem.execute());
            }
        }
        return dto;
    }

    @Override
    public EvaluationResultResponseDTO doEvaluation(UUID pricingTypeId, EvaluationRequestDTO data, UUID managementEntity) {
        if (pricingTypeId == null)
            throw new IllegalArgumentException("ID is required for evaluation");
        PricingType pricingType = pricingTypeRepository.findByUuidAndManagementEntityAndDeletedIsFalse(pricingTypeId, managementEntity)
                .orElseThrow(() -> new NoSuchElementException("Pricing type not found"));

        Formula formula = formulaService.getEffectiveEntityByPricingType(pricingType, managementEntity);
        EvaluateFormula evaluateFormula = new EvaluateFormula(formula, data.getFields());
        EvaluationResultResponseDTO result = new EvaluationResultResponseDTO(evaluateFormula.execute());
        System.err.println("Final expression to evaluate: " + formula.getExpression() + " => " + evaluateFormula.execute());
        return result;
    }

}