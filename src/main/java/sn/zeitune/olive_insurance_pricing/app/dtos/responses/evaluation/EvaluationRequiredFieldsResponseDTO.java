package sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionValueResponseDTO;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "DTO de réponse pour connaitre les données nécessaires pour l'évaluation d'une formule")
public class EvaluationRequiredFieldsResponseDTO {

    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public abstract static class EvaluationRequiredField {
        private String name;
        private String type;
    }

    @SuperBuilder
    @AllArgsConstructor
    @Data
    public static class EvaluationRequiredNumericField extends EvaluationRequiredField {
    }

    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class EvaluationRequiredSelectField extends EvaluationRequiredField {
        private List<SelectFieldOptionValueResponseDTO> options; // Default value if not specified
    }


    List<EvaluationRequiredField> requiredFields = new ArrayList<>();

    public void addFieldList(List<EvaluationRequiredField> fields) {
        this.requiredFields.addAll(fields);

        // delete all duplicates values
        for (int i = 0; i < requiredFields.size(); i++) {
            EvaluationRequiredField field = requiredFields.get(i);
            for (int j = i + 1; j < requiredFields.size(); j++) {
                if (field.getName().equals(requiredFields.get(j).getName())) {
                    requiredFields.remove(j);
                    j--;
                }
            }
        }
    }
}
