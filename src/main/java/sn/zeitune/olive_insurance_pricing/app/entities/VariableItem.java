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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class VariableItem {

    @Id
    @Column(name = "code_variable")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variable_item_seq")
    @SequenceGenerator(name = "variable_item_seq", sequenceName = "variable_item_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "uuid", nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @PrePersist
    public void generateUuid() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }

    @Column(nullable = false)
    private String label;

    @Column
    private String description;

    @Column(name = "variable_name", nullable = false)
    private String variableName;

    @Column(name = "to_return")
    private Boolean toReturn;

    @Column(name = "management_entity")
    private UUID managementEntity;

    @Column
    private UUID product;

    @Column
    private UUID coverage;

}