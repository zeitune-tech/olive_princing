package sn.zeitune.olive_insurance_pricing.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
    @Column
    private UUID product;
    @Column
    private UUID branch;
}