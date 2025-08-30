package sn.zeitune.olive_insurance_pricing.app.controllers.variableItem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.VariableItemService;
import sn.zeitune.olive_insurance_pricing.security.Employee;

import java.util.List;

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
        return ResponseEntity.ok(variableItemService.retrieveAllActive(employee.getManagementEntity()));
    }

}