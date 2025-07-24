package sn.zeitune.olive_insurance_pricing.app.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.Condition;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.NumericalCondition;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "regle")
@ToString
public class Rule {

    @Id
    @Column(name = "code_regle")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regle_seq")
    @SequenceGenerator(name = "regle_seq", sequenceName = "regle_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "uuid", nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @PrePersist
    public void generateUuid() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }

    private String label;
    private String name;
    private Double value;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "regle_conditions",
            joinColumns = @JoinColumn(name = "code_regle"),
            inverseJoinColumns = @JoinColumn(name = "code_condition")
    )
    private Set<Condition> conditions = new HashSet<>();
}