package sn.zeitune.olive_insurance_pricing.app.services;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.evaluation.EvaluationRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationRequiredFieldsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationResultResponseDTO;

import java.util.List;
import java.util.UUID;

public interface EvaluationService {

    EvaluationRequiredFieldsResponseDTO getRequiredFields(UUID id);
    List<EvaluationResultResponseDTO> doEvaluation(
            UUID pricingTypeId, EvaluationRequestDTO data,
            UUID managementEntity
    );
}
