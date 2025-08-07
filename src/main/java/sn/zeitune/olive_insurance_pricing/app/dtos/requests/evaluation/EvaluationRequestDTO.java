package sn.zeitune.olive_insurance_pricing.app.dtos.requests.evaluation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionValueResponseDTO;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "DTO pour l'évaluation d'une formule")
public class EvaluationRequestDTO {

//    @SuperBuilder
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Data
//    public abstract static class EvaluationRequestField {
//        private String name;
//    }
//
//    @SuperBuilder
//    @AllArgsConstructor
////    @NoArgsConstructor
//    @Data
//    public static class EvaluationRequestNumericField extends EvaluationRequestField {
//        private Double value;
//    }
//
//    @SuperBuilder
//    @AllArgsConstructor
////    @NoArgsConstructor
//    @Data
//    public static class EvaluationRequestSelectField extends EvaluationRequestField {
//        private UUID option;
//    }

    UUID id;
    Map<String, Object> fields = new HashMap<>();

}
