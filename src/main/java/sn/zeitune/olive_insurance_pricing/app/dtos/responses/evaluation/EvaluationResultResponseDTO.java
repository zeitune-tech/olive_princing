package sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionValueResponseDTO;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Schema(description = "DTO de réponse de l'évaluation d'une formule")
public class EvaluationResultResponseDTO {

    Double value;

    public static class EvaluationResultVariable {
        Double value;

    }

    public static class EvaluationResultVariableCondition extends EvaluationResultVariable {
        Double value;

    }

    public static class EvaluationResultFormula extends EvaluationResultVariable {
        Double value;

    }

}
