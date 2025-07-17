package sn.zeitune.olive_insurance_pricing.app.dtos.requests;

public record SelectFieldOptionValueRequestDTO(
        String label,
        String name,
        String group
) {
}
