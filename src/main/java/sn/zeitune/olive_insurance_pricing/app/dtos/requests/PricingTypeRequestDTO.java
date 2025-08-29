package sn.zeitune.olive_insurance_pricing.app.dtos.requests;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PricingTypeRequestDTO {
    @NotNull(message = "Le nom est obligatoire")
    private String name;
    private String description;
    @NotNull(message = "Le produit est obligatoire")
    private UUID product;
    @NotNull(message = "La branche est obligatoire")
    private UUID branch;
    @FutureOrPresent(message = "La date effective ne peut pas être dans le passé")
    private LocalDate dateEffective;
}
