package sn.zeitune.olive_insurance_pricing.app.dtos.requests.field;

import lombok.Builder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

@Builder
public record SelectFieldOptionsRequestDTO(
    String label,

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    String name,
    
    @Size(max = 500, message = "Description must not exceed 500 characters")
    String description,

    Set<UUID> possibilities
){}
