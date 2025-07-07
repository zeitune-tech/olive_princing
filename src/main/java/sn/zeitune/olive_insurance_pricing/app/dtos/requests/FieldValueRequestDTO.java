package sn.zeitune.olive_insurance_pricing.app.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Builder
public record FieldValueRequestDTO (
    
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    String name,
    
    @Size(max = 500, message = "Description must not exceed 500 characters")
    String description,
    
    @Size(max = 255, message = "Value must not exceed 255 characters")
    String value
){}
