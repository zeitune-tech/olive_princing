package sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.requiredFieldsForVariableItem;

import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationRequiredFieldsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;

import java.util.List;

public abstract class GetRequiredFieldsForVariableItem {

    public static GetRequiredFieldsForVariableItem create(VariableItem variableItem) {
        if (variableItem instanceof Formula) {
            return new GetRequiredFieldsForFormula(variableItem);
        } else if (variableItem instanceof VariableCondition) {
            return new GetRequiredFieldsForVariableCondition(variableItem);
        } else if (variableItem instanceof NumericField) {
            return new GetRequiredFieldsForNumericField(variableItem);
        } else if (variableItem instanceof SelectField) {
            return new GetRequiredFieldsForSelectField(variableItem);
        } else {
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

