package sn.zeitune.olive_insurance_pricing.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "type_tarif")
@ToString
public class PricingType extends BaseEntity {
    private String name;
    private String description;
    private UUID product;
    private UUID branch;
}
