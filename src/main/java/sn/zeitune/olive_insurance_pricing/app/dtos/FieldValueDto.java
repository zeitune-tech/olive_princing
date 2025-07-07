package sn.zeitune.olive_insurance_pricing.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "DTO représentant une valeur de champ")
public class FieldValueDto extends VariableItemDto {
    
    @Schema(description = "Nom de la valeur de champ", example = "MASCULIN", required = true)
    private String name;
    
    @Schema(description = "Description de la valeur de champ", example = "Sexe masculin")
    private String description;
    
    @Schema(description = "Valeur associée", example = "M")
    private String value;
}
