package sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.variableEvaluation.numericResult;

import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.Rule;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.condition.Condition;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.condition.NumericCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.condition.SelectCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectFieldOptionValue;
import sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.variableEvaluation.EvaluateSelectField;

import java.util.Map;

public class EvaluateVariableCondition extends EvaluateNumericResultVariable {

    final VariableCondition variableCondition;
    final Map<String, Object> fields;

    public EvaluateVariableCondition(VariableCondition variableCondition, Map<String, Object> fields) {
        this.variableCondition = variableCondition;
        this.fields = fields;
    }

    @Override
    public Double execute() {
        for (Rule rule : variableCondition.getRules()) {
            if (isRuleValid(rule)) {
                // If the rule is valid, we can return the value of the variable condition
                System.err.println("Rule is valid: " + rule + " " + rule.getValue());
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
            } else if (condition instanceof SelectCondition selectCondition) {
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

