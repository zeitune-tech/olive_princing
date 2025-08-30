package sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.entities.BaseEntity;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.condition.Condition;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "regle")
@ToString
public class Rule extends BaseEntity {
    private String label;
    private String name;
    private Double value;
    @Builder.Default
    @ManyToMany(cascade = {CascadeType.REMOVE})
    @JoinTable(
            name = "regle_conditions",
            joinColumns = @JoinColumn(name = "code_regle"),
            inverseJoinColumns = @JoinColumn(name = "code_condition")

    )
    private Set<Condition> conditions = new HashSet<>();
}