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
@Table(name = "variable_condition")
public class VariableCondition extends VariableItem {

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "regles",
            joinColumns = @JoinColumn(name = "code_variable"),
            inverseJoinColumns = @JoinColumn(name = "code_regle")
    )
    private Set<Rule> rules = new HashSet<>();
}
