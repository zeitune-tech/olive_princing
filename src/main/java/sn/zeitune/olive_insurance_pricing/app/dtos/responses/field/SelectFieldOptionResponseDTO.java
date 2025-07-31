package sn.zeitune.olive_insurance_pricing.app.dtos.responses.field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectFieldOptionResponseDTO {

    private UUID id;
    private String label;
    private String name;
    private String description;
    private List<SelectFieldOptionValueResponseDTO> possibilities;

}
