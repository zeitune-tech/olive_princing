package sn.zeitune.olive_insurance_pricing.app.dtos.responses;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacteristicResponse {

    private UUID uuid;

    private String code;

    private String name;

    private String type;

    private String unit;

    private List<String> options;

    private Double minValue;

    private Double maxValue;

    private String defaultValue;

    private String scope;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isExternalTable;

    private String tableName;

    private String tableColumn;
}
