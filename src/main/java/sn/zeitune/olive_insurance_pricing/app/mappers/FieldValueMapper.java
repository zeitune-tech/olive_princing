package sn.zeitune.olive_insurance_pricing.app.mappers;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FieldValueRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FieldValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.FieldValue;

public class FieldValueMapper {

    public static FieldValueResponseDTO toResponseDTO(FieldValue fieldValue) {
        return FieldValueResponseDTO.builder()
                .id(fieldValue.getId())
                .name(fieldValue.getValue())
                .description(fieldValue.getDescription())
                .value(fieldValue.getValue())
                .build();
    }

    public static FieldValue toEntity(FieldValueRequestDTO requestDTO) {
        return FieldValue.builder()
                .name(requestDTO.value())
                .description(requestDTO.description())
                .value(requestDTO.value())
                .build();
    }

    public static void updateEntityFromRequestDTO(FieldValue fieldValue, FieldValueRequestDTO requestDTO) {
        fieldValue.setName(requestDTO.value());
        fieldValue.setDescription(requestDTO.description());
        fieldValue.setValue(requestDTO.value());
    }

    // Backward compatibility
    public static FieldValueResponseDTO map(FieldValue fieldValue) {
        return toResponseDTO(fieldValue);
    }
}
