package sn.zeitune.olive_insurance_pricing.app.services.impl;

import lombok.RequiredArgsConstructor;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.evaluation.EvaluationRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationRequiredFieldsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationResultResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.Rule;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.Condition;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.NumericCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.SelectCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldOptionValueMapper;
import sn.zeitune.olive_insurance_pricing.app.services.EvaluationService;
import sn.zeitune.olive_insurance_pricing.app.services.FormulaService;
import sn.zeitune.olive_insurance_pricing.app.services.VariableItemService;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hibernate.query.sqm.ComparisonOperator.LESS_THAN_OR_EQUAL;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final FormulaService formulaService;
    private final VariableItemService variableItemService;

    private static abstract class GetRequiredFieldsForVariableItem {

        public static GetRequiredFieldsForVariableItem create(VariableItem variableItem) {
            if (variableItem instanceof Formula) {
                return new GetRequiredFieldsForFormula(variableItem);
            } else if (variableItem instanceof VariableCondition) {
                return new GetRequiredFieldsForVariableCondition(variableItem);
            } else if (variableItem instanceof NumericField) {
                return new GetRequiredFieldsForNumericField(variableItem);
            } else if (variableItem instanceof SelectField) {
                return new GetRequiredFieldsForSelectField(variableItem);
            }
            else {
                throw new IllegalArgumentException("Unsupported variable item : " + variableItem.getVariableName());
            }
        }

        private final VariableItem variableItem;

        public GetRequiredFieldsForVariableItem(VariableItem variableItem) {
            this.variableItem = variableItem;
        }

        public String getName() {
            return variableItem.getVariableName();
        }

        public abstract List<EvaluationRequiredFieldsResponseDTO.EvaluationRequiredField> execute();
    }

    private static  class GetRequiredFieldsForFormula extends GetRequiredFieldsForVariableItem {

        private final Formula formula;

        public GetRequiredFieldsForFormula(VariableItem variableItem) {
            super(variableItem);
            this.formula = (Formula) variableItem;
        }

        @Override
        public List<EvaluationRequiredFieldsResponseDTO.EvaluationRequiredField> execute() {
            List<EvaluationRequiredFieldsResponseDTO.EvaluationRequiredField> fields = new ArrayList<>();
            this.formula.getVariables().forEach(variableItem -> {
                GetRequiredFieldsForVariableItem getRequiredFieldsForVariableItem = GetRequiredFieldsForVariableItem.create(variableItem);
                fields.addAll(getRequiredFieldsForVariableItem.execute());
            });
            return fields;
        }
    }

    private static class GetRequiredFieldsForVariableCondition extends GetRequiredFieldsForVariableItem {

        private final VariableCondition variableCondition;

        public GetRequiredFieldsForVariableCondition(VariableItem variableItem) {
            super(variableItem);
            this.variableCondition = (VariableCondition) variableItem;
        }

        @Override
        public List<EvaluationRequiredFieldsResponseDTO.EvaluationRequiredField> execute() {
            List<EvaluationRequiredFieldsResponseDTO.EvaluationRequiredField> fields = new ArrayList<>();
            variableCondition.getRules().forEach(
                    rule -> {
                        rule.getConditions().forEach(condition -> {
                            GetRequiredFieldsForVariableItem getRequiredFieldsForVariableItem = null;
                            if (condition instanceof NumericCondition) {
                                getRequiredFieldsForVariableItem = GetRequiredFieldsForVariableItem.create(((NumericCondition) condition).getNumericField());
                            } else if (condition instanceof SelectCondition) {
                                getRequiredFieldsForVariableItem = GetRequiredFieldsForVariableItem.create(((SelectCondition) condition).getSelectField());
                            }
                            if (getRequiredFieldsForVariableItem == null) {
                                throw new IllegalArgumentException("Unsupported condition type: " + condition.getClass().getName());
                            }
                            fields.addAll(getRequiredFieldsForVariableItem.execute());

                        });
                    }
            );
            return fields;
        }
    }

    private static class GetRequiredFieldsForNumericField extends  GetRequiredFieldsForVariableItem {
        public GetRequiredFieldsForNumericField(VariableItem variableItem) {
            super(variableItem);
        }

        @Override
        public List<EvaluationRequiredFieldsResponseDTO.EvaluationRequiredField> execute() {
            return List.of(
                    EvaluationRequiredFieldsResponseDTO.EvaluationRequiredNumericField.builder()
                            .name(getName())
                            .type(FieldType.NUMBER.getTypes().get(0))
                            .build()
            );
        }
    }

    private static class GetRequiredFieldsForSelectField extends GetRequiredFieldsForVariableItem {

        private final SelectField selectField;

        public GetRequiredFieldsForSelectField(VariableItem variableItem) {
            super(variableItem);
            this.selectField = (SelectField) variableItem;
        }

        @Override
        public List<EvaluationRequiredFieldsResponseDTO.EvaluationRequiredField> execute() {
            return List.of(
                    EvaluationRequiredFieldsResponseDTO.EvaluationRequiredSelectField.builder()
                            .name(getName())
                            .type(FieldType.SELECT.getTypes().get(0))
                            .options(
                                    selectField.getOptions().getPossibilities().stream()
                                            .map(SelectFieldOptionValueMapper::map)
                                            .toList()
                            )
                            .build()
            );
        }

    }


    private static abstract class EvaluateNumericResultVariable {

        public static EvaluateNumericResultVariable create(VariableItem variableItem, Map<String, Object> fields) {
            if (variableItem instanceof Formula) {
                return new EvaluateFormula((Formula) variableItem, fields);
            } else if (variableItem instanceof VariableCondition) {
                return new EvaluateVariableCondition((VariableCondition) variableItem, fields);
            } else if (variableItem instanceof NumericField) {
                return new EvaluateNumericField((NumericField) variableItem, fields);
            }
            throw new IllegalArgumentException("Unsupported variable item type: " + variableItem.getClass().getName());
        }

        public abstract Double execute();
    }

    private static class EvaluateFormula extends  EvaluateNumericResultVariable {

        Formula formula;
        Map<String, Object> fields;

        public EvaluateFormula(Formula formula, Map<String, Object> fields) {
            this.formula = formula;
            this.fields = fields;
        }

        @Override
        public Double execute() {
            for (VariableItem variableItem : formula.getVariables()) {
                if (!(variableItem instanceof VariableCondition || variableItem instanceof Formula || variableItem instanceof NumericField))
                    throw new IllegalArgumentException("Unsupported variable item type: " + variableItem.getClass().getName());

                EvaluateNumericResultVariable evaluateVariableItem = EvaluateNumericResultVariable.create(variableItem, fields);
                Double value = evaluateVariableItem.execute();
                if (value == null) {
                    throw new IllegalArgumentException("Variable " + variableItem.getVariableName() + " is not defined");
                }
                // Replace the variable in the formula expression with its value
                System.err.print("Expression to evaluate: "+ formula.getExpression());
                formula.setExpression(formula.getExpression().replace("{{" + variableItem.getVariableName() + "}}", String.valueOf(value)));
                System.err.println(" => "  + formula.getExpression());
            }
            Expression expression = new ExpressionBuilder(formula.getExpression()).build();
            return expression.evaluate();
        }
    }

    private static class EvaluateVariableCondition extends  EvaluateNumericResultVariable {

        final VariableCondition variableCondition;
        final Map<String, Object> fields;

        public EvaluateVariableCondition(VariableCondition variableCondition, Map<String, Object> fields) {
            this.variableCondition = variableCondition;
            this.fields = fields;
        }

        @Override
        public Double execute() {
            for ( Rule rule : variableCondition.getRules() ) {
                if (isRuleValid(rule)) {
                    // If the rule is valid, we can return the value of the variable condition
                    System.err.println("Rule is valid: " + rule.toString() + " " + rule.getValue());
                    return rule.getValue();
                }
            }

            return 0.;
        }

        private boolean isRuleValid(Rule rule) {
            for (Condition condition : rule.getConditions()) {
                if (condition instanceof NumericCondition numericCondition) {
                    if (fields.containsKey(numericCondition.getNumericField().getVariableName())) {
                        EvaluateNumericField evaluateNumericField = new EvaluateNumericField(numericCondition.getNumericField(), fields);
                        double fieldValue = evaluateNumericField.execute();
                        switch (numericCondition.getNumericOperator()) {
                            case EQUALS:
                                if (numericCondition.getNumericValue() == null) {
                                    throw new IllegalArgumentException("Numeric condition value is required for EQUALS operator");
                                }
                                if (fieldValue != numericCondition.getNumericValue()) {
                                    return false; // Condition not met
                                }
                                break;
                            case NOT_EQUALS:
                                if (numericCondition.getNumericValue() == null) {
                                    throw new IllegalArgumentException("Numeric condition value is required for NOT_EQUALS operator");
                                }
                                if (fieldValue == numericCondition.getNumericValue()) {
                                    return false; // Condition not met
                                }
                                break;

                            case LESS_THAN:
                                if (numericCondition.getNumericValue() == null) {
                                    throw new IllegalArgumentException("Numeric condition value is required for LESS_THAN operator");
                                }
                                if (fieldValue >= numericCondition.getNumericValue()) {
                                    return false; // Condition not met
                                }
                                break;
                            case LESS_OR_EQUAL:
                                if (numericCondition.getNumericValue() == null) {
                                    throw new IllegalArgumentException("Numeric condition value is required for LESS_THAN_OR_EQUAL operator");
                                }
                                if (fieldValue > numericCondition.getNumericValue()) {
                                    return false; // Condition not met
                                }
                                break;

                            case GREATER_THAN:
                                if (numericCondition.getNumericValue() == null) {
                                    throw new IllegalArgumentException("Numeric condition value is required for GREATER_THAN operator");
                                }
                                if (fieldValue <= numericCondition.getNumericValue()) {
                                    return false; // Condition not met
                                }
                                break;

                            case GREATER_OR_EQUAL:
                                if (numericCondition.getNumericValue() == null) {
                                    throw new IllegalArgumentException("Numeric condition value is required for GREATER_THAN_OR_EQUAL operator");
                                }
                                if (fieldValue < numericCondition.getNumericValue()) {
                                    return false; // Condition not met
                                }
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + numericCondition.getNumericOperator());
                        }
                    }
                }
                else if (condition instanceof SelectCondition selectCondition) {
                    if (fields.containsKey(selectCondition.getSelectField().getVariableName())) {
                        EvaluateSelectField evaluateSelectField = new EvaluateSelectField(selectCondition.getSelectField(), fields);
                        SelectFieldOptionValue fieldValue = evaluateSelectField.execute();
                        switch (selectCondition.getSelectFieldOperator()) {
                            case EQUALS:
                                if (selectCondition.getSelectFieldValue() == null)
                                    throw new IllegalArgumentException("Select condition value is required for EQUALS operator");
                                if (!fieldValue.getName().equals(selectCondition.getSelectFieldValue().getName()))
                                    return false; // Condition not met

                                break;
                            case NOT_EQUALS:
                                if (selectCondition.getSelectFieldValue() == null)
                                    throw new IllegalArgumentException("Select condition value is required for NOT_EQUALS operator");
                                if (fieldValue.getName().equals(selectCondition.getSelectFieldValue().getName()))
                                    return false; // Condition not met
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + selectCondition.getSelectFieldOperator());
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Unsupported condition type: " + condition.getClass().getName());
                }
            }
            // If all conditions are met, return true
            return true; // All conditions are met
        }
    }


    private static class EvaluateNumericField extends  EvaluateNumericResultVariable {

        final NumericField numericField;
        final Map<String, Object> fields;

        public EvaluateNumericField(NumericField numericField, Map<String, Object> fields) {
            this.numericField = numericField;
            this.fields = fields;
        }

        @Override
        public Double execute() {
            if (!fields.containsKey(numericField.getVariableName()))
                throw new IllegalArgumentException("NumericField " + numericField.getVariableName() + " is required but not provided");

            Object value = fields.get(numericField.getVariableName());

            if (!(value instanceof Number))
                throw new IllegalArgumentException("NumericField " + numericField.getVariableName() + " is not a number");

            return ((Number) value).doubleValue();

        }

    }

    private static class EvaluateSelectField  {

        final SelectField selectField;
        final Map<String, Object> fields;

        public EvaluateSelectField(SelectField selectField, Map<String, Object> fields) {
            this.selectField = selectField;
            this.fields = fields;
        }

        public SelectFieldOptionValue execute() {
            if (!fields.containsKey(selectField.getVariableName()))
                throw new IllegalArgumentException("SelectField " + selectField.getVariableName() + " is required but not provided");

            SelectFieldOptionValue selectFieldOptionValue = selectField.getOptions().getPossibilities().stream()
                    .filter(option ->
                        option.getUuid().toString().equals(fields.get(selectField.getVariableName()))
                    )
                    .findFirst().orElse(null);

//            System.err.println("SelectFieldOptionValue: " + fields.get(selectField.getVariableName()));
            if (selectFieldOptionValue == null)
                throw new IllegalArgumentException("SelectField " + selectField.getVariableName() + " is not a valid select value");

            return selectFieldOptionValue;
        }

    }



    @Override
    public EvaluationRequiredFieldsResponseDTO getRequiredFields(UUID id) {

        EvaluationRequiredFieldsResponseDTO dto = new EvaluationRequiredFieldsResponseDTO();

        Formula formula = formulaService.getEntityByUuid(id);

        if (formula.getExpression() != null) {
            Matcher matcher = Pattern.compile("\\{\\{(.*?)}}").matcher(formula.getExpression());
            while (matcher.find()) {
                String variableName = matcher.group(1).trim();
                GetRequiredFieldsForVariableItem getRequiredFieldsForVariableItem = GetRequiredFieldsForVariableItem.create(variableItemService.findByVariableName(variableName));
                dto.addFieldList(getRequiredFieldsForVariableItem.execute());
            }
        }

        return dto;
    }

    @Override
    public EvaluationResultResponseDTO doEvaluation(EvaluationRequestDTO data) {
        if (data.getId() == null)
            throw new IllegalArgumentException("ID is required for evaluation");

        Formula formula = formulaService.getEntityByUuid(data.getId());
        EvaluateFormula evaluateFormula = new EvaluateFormula(formula, data.getFields());
        EvaluationResultResponseDTO result = new EvaluationResultResponseDTO(evaluateFormula.execute());
        System.err.println("Final expression to evaluate: " + formula.getExpression() + " => " + evaluateFormula.execute());
        return result;
    }

}
