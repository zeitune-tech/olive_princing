package sn.zeitune.olive_insurance_pricing.app.dtos.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import sn.zeitune.olive_insurance_pricing.enums.CharacteristicType;

import java.time.LocalDate;
import java.util.List;
public record CharacteristicRequest(

        @NotBlank
        String code,

        @NotBlank
        String name,

        @NotNull
        CharacteristicType type,

        String unit,

        // Optional, validated only if type is LIST
        List<@NotBlank String> options,

        @DecimalMin(value = "0.0", inclusive = true, message = "Min value cannot be negative")
        Double minValue,

        @DecimalMin(value = "0.0", inclusive = true, message = "Max value cannot be negative")
        Double maxValue,

        String defaultValue,

        @NotBlank
        @Pattern(regexp = "POLICY|RISK", message = "Scope must be either POLICY or RISK")
        String scope,

        LocalDate startDate,

        LocalDate endDate,

        String tableName,

        String tableColumn
) {}