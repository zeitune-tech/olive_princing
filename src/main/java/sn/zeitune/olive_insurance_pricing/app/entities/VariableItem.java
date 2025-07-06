package sn.zeitune.olive_insurance_pricing.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity(name = "variable_item")
@NoArgsConstructor
@AllArgsConstructor
public abstract class VariableItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String managementEntity;

    @Column
    private UUID product;

    @Column
    private UUID coverage;
}
