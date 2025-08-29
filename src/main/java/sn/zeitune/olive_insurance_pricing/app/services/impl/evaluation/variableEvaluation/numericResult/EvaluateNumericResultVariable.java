package sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.variableEvaluation.numericResult;

import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;

import java.util.Map;

public abstract class EvaluateNumericResultVariable {

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

