package sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.variableEvaluation.numericResult;

import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.NumericField;

import java.util.Map;

public class EvaluateNumericField extends EvaluateNumericResultVariable {

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
