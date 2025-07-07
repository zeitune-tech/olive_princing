package sn.zeitune.olive_insurance_pricing.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class VariableItemDto {
    
    private Long id;
    private UUID uuid;
    private String label;
    private String description;
    private String variableName;
    private Boolean toReturn;
    private String managementEntity;
    private UUID product;
    private UUID coverage;
}
