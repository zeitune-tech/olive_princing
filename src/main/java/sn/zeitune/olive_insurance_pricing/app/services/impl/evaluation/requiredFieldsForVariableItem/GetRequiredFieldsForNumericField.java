package sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.requiredFieldsForVariableItem;

import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationRequiredFieldsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.VariableItem;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.List;

public class GetRequiredFieldsForNumericField extends GetRequiredFieldsForVariableItem {
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

