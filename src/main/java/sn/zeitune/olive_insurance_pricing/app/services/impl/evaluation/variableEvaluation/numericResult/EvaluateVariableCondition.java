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
            }else {
//                System.err.println("Rule is not valid: " + rule + " " + rule.getValue());
            }
        }
        return 0.;
    }

    private boolean isRuleValid(Rule rule) {
        for (Condition condition : rule.getConditions()) {
            if (condition instanceof NumericCondition numericCondition) {
                EvaluateNumericField evaluateNumericField = new EvaluateNumericField(numericCondition.getNumericField(), fields);
                double fieldValue = evaluateNumericField.execute();
                if (!numericCondition.getNumericValue().isNaN()) {
                    System.err.println("Checking operator for " + numericCondition.getNumericField().getVariableName() + " value: " + fieldValue + " condition value: " + numericCondition.getNumericValue() + " operator: " + numericCondition.getNumericOperator());
                    switch (numericCondition.getNumericOperator()) {
                        case EQUALS:
                            if (fieldValue != numericCondition.getNumericValue())
                                return false; // Condition not met
                            break;
                        case NOT_EQUALS:
                            if (fieldValue == numericCondition.getNumericValue())
                                return false; // Condition not met
                            break;
                        case LESS_THAN:
                            if (fieldValue >= numericCondition.getNumericValue())
                                return false; // Condition not met
                            break;
                        case LESS_OR_EQUAL:
                            if (fieldValue > numericCondition.getNumericValue())
                                return false; // Condition not met
                            break;
                        case GREATER_THAN:
                            if (fieldValue <= numericCondition.getNumericValue())
                                return false; // Condition not met
                            break;
                        case GREATER_OR_EQUAL:
                            if (fieldValue < numericCondition.getNumericValue())
                                return false; // Condition not met
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + numericCondition.getNumericOperator());
                    }
                } else {
                    System.err.println("Checking min/max for " + numericCondition.getNumericField().getVariableName() + " value: " + fieldValue + " min: " + numericCondition.getMinimum() + " max: " + numericCondition.getMaximum());
                    if (fieldValue > numericCondition.getMaximum()) return false;
                    if (fieldValue < numericCondition.getMinimum()) return false;
                }
            } else if (condition instanceof SelectCondition selectCondition) {
                EvaluateSelectField evaluateSelectField = new EvaluateSelectField(selectCondition.getSelectField(), fields);
                SelectFieldOptionValue fieldValue = evaluateSelectField.execute();
                switch (selectCondition.getSelectFieldOperator()) {
                    case EQUALS:
                        if (!fieldValue.getName().equals(selectCondition.getSelectFieldValue().getName()))
                            return false; // Condition not met
                        break;
                    case NOT_EQUALS:
                        if (fieldValue.getName().equals(selectCondition.getSelectFieldValue().getName()))
                            return false; // Condition not met
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + selectCondition.getSelectFieldOperator());
                }
            } else {
                throw new IllegalArgumentException("Unsupported condition type: " + condition.getClass().getName());
            }
        }
        // If all conditions are met, return true
        return true; // All conditions are met
    }
}