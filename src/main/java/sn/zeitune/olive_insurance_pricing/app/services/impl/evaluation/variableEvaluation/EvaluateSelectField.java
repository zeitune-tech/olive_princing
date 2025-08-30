package sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.variableEvaluation;

import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectFieldOptionValue;

import java.util.Map;

public class EvaluateSelectField {

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
                        option.getName().equals(fields.get(selectField.getVariableName()))
                )
                .findFirst().orElse(null);

        if (selectFieldOptionValue == null)
            throw new IllegalArgumentException("SelectField " + selectField.getVariableName() + " is not a valid select value");

        return selectFieldOptionValue;
    }

}

