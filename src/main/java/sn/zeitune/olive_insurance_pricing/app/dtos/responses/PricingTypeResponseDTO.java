package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PricingTypeResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private UUID product;
    private  UUID branch;
    private LocalDate dateEffective;
    @Schema(description = "Date de création", example = "2023-10-01T12:00:00Z")
    Instant createdAt;
    @Schema(description = "Date de mise à jour", example = "2023-10-01T12:00:00Z")
    Instant updatedAt;
}
