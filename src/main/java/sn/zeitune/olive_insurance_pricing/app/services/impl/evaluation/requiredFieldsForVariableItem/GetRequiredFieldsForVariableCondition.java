package sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.requiredFieldsForVariableItem;

import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationRequiredFieldsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.NumericCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.SelectCondition;

import java.util.ArrayList;
import java.util.List;

public class GetRequiredFieldsForVariableCondition extends GetRequiredFieldsForVariableItem {

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
