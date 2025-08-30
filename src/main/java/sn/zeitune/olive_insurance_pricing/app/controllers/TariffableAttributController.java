package sn.zeitune.olive_insurance_pricing.app.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.TariffableObjectResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.services.TariffableAttributService;

import java.util.List;

@RestController
@RequestMapping("/app/tariffable-attributs")
@RequiredArgsConstructor
@Slf4j
public class TariffableAttributController {

    private final TariffableAttributService tariffableAttributService;

    @GetMapping
    public ResponseEntity<List<TariffableObjectResponseDTO>> getAll(
            Authentication authentication
    ) {
        log.info("Getting all tariffable-attributs");
        List<TariffableObjectResponseDTO> response = tariffableAttributService.getAllTariffableObjects();
        log.info("Got {} tariffable-attributs", response.size());
        return ResponseEntity.ok(response);
    }

}
