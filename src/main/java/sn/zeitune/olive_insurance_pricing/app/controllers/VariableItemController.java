package sn.zeitune.olive_insurance_pricing.app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.VariableItemService;
import sn.zeitune.olive_insurance_pricing.security.Employee;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/app/variable-items")
@RequiredArgsConstructor
@Slf4j
public class VariableItemController {

    private final VariableItemService variableItemService;

    @GetMapping("/all")
    public ResponseEntity<List<VariableItemResponseDTO>> getAll(
            Authentication authentication
    ) {
        Employee employee = (Employee) authentication.getPrincipal();
        return ResponseEntity.ok(variableItemService.retrieveAll(employee.getManagementEntity()));
    }

}