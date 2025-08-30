package sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.requiredFieldsForVariableItem;

import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationRequiredFieldsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.VariableItem;

import java.util.ArrayList;
import java.util.List;

public class GetRequiredFieldsForFormula extends GetRequiredFieldsForVariableItem {

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

