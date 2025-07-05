package sn.zeitune.olive_insurance_pricing.app.dtos.requests;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public record CharacteristicUpdateRequest(
        String name,
        String type,
        String unit,
        List<String> options,
        Double minValue,
        Double maxValue,
        String defaultValue,
        String scope,
        LocalDate startDate,
        LocalDate endDate,
        Boolean isExternalTable,
        String tableName,
        String tableColumn
) {}