package sn.zeitune.olive_insurance_pricing.app.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PricingTypeRequestDTO {
    private String name;
    private String description;
    private UUID product;
    private UUID branch;
}
