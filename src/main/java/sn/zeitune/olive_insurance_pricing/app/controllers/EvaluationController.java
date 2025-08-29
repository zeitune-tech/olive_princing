package sn.zeitune.olive_insurance_pricing.app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.evaluation.EvaluationRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.PricingTypeResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationRequiredFieldsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.evaluation.EvaluationResultResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;
import sn.zeitune.olive_insurance_pricing.app.services.ConstantService;
import sn.zeitune.olive_insurance_pricing.app.services.EvaluationService;
import sn.zeitune.olive_insurance_pricing.security.Employee;

import java.util.UUID;

@RestController
@RequestMapping("/app/evaluations")
@RequiredArgsConstructor
@Slf4j
public class EvaluationController {

    private final EvaluationService evaluationService;

    @GetMapping("/formula/{id}")
    public ResponseEntity<EvaluationRequiredFieldsResponseDTO> getRequiredFields(
            @PathVariable UUID id
        ) {
        log.info("REST request to get canvas for calcul formula: {}", id);
        return ResponseEntity.ok(evaluationService.getRequiredFields(id));
    }

    @PostMapping("/compute/{pricingTypeId}")
    public ResponseEntity<EvaluationResultResponseDTO> evaluate(
            @PathVariable UUID pricingTypeId,
            @Valid @RequestBody EvaluationRequestDTO evaluationRequestDTO,
            Authentication authentication
        ) {
        Employee employee = (Employee) authentication.getPrincipal();
        log.info("REST request to evaluate with pricing type: {}", pricingTypeId);
        return ResponseEntity.ok(evaluationService.doEvaluation(pricingTypeId, evaluationRequestDTO, employee.getManagementEntity()));
    }

}
