package sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Schema(description = "DTO de réponse de l'évaluation d'une formule")
public class EvaluationResultResponseDTO {

    Double value;
    UUID coverage;

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
