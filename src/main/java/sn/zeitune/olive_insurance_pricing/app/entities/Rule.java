package sn.zeitune.olive_insurance_pricing.app.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("regle")
public class Rule extends VariableItem {

    private Double value;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "conditions",
            joinColumns = @JoinColumn(name = "code_variable"),
            inverseJoinColumns = @JoinColumn(name = "code_condition")
    )
    private Set<Condition> conditions = new HashSet<>();
}
