package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.http.ResponseEntity;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.evaluation.EvaluationRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.PricingTypeResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationRequiredFieldsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationResultResponseDTO;

import java.util.UUID;

public interface EvaluationService {

    EvaluationRequiredFieldsResponseDTO getRequiredFields(UUID id);
    EvaluationResultResponseDTO doEvaluation(
            UUID pricingTypeId, EvaluationRequestDTO data,
            UUID managementEntity
    );
}
