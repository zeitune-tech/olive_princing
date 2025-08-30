package sn.zeitune.olive_insurance_pricing.app.clients.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sn.zeitune.olive_insurance_pricing.app.clients.VehicleSettingsClient;
import sn.zeitune.olive_insurance_pricing.app.dtos.externals.CoverageExternalDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.externals.ManagementEntityExternalDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.externals.ProductExternalDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.externals.TariffableAttributExternalDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
@Slf4j
public class VehicleSettingsClientImpl implements VehicleSettingsClient {

    private final WebClient vehicleSettingsWebClient;

    public VehicleSettingsClientImpl(
            WebClient.Builder webClientBuilder,
            @Value("${services.olive-insurance-settings-service.base-url}") String vehicleSettingsServiceUrl
    ) {
        this.vehicleSettingsWebClient = webClientBuilder.baseUrl(vehicleSettingsServiceUrl).build();
    }

    private Mono<Throwable> handleError(ClientResponse response) {
        return response.bodyToMono(String.class).flatMap(errorBody -> {
            log.error("❗ Error response from user-service: status={} body={}", response.statusCode(), errorBody);
            return Mono.error(new RuntimeException("User service call failed: " + errorBody));
        });
    }

    @Override
    public List<CoverageExternalDTO> getCoveragesByProduct(UUID product, UUID managementEntity) {
        return vehicleSettingsWebClient.get()
                .uri("/inter-services/coverages/products/{product}/coverages?managementEntity={managementEntity}", product, managementEntity)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToFlux(CoverageExternalDTO.class)
                .collectList()
                .block();
    }


}