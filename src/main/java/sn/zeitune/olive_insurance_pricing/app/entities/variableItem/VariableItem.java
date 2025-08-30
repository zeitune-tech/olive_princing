package sn.zeitune.olive_insurance_pricing.app.entities.variableItem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.entities.BaseEntity;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "variable_item")
public abstract class VariableItem extends BaseEntity {
    @Column(nullable = false)
    private String label;
    @Column
    private String description;
    @Column(name = "variable_name", nullable = false)
    private String variableName;
    @Column(name = "to_return")
    private Boolean toReturn;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private PricingType pricingType;
    @Column(name = "garantie", nullable = false)
    private UUID coverage;
    @Column(name = "date_effective", nullable = false)
    private LocalDate dateEffective;
}