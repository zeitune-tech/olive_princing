package sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.requiredFieldsForVariableItem;

import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationRequiredFieldsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.field.SelectFieldOptionValueMapper;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.List;

public class GetRequiredFieldsForSelectField extends GetRequiredFieldsForVariableItem {

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

