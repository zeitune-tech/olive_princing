package sn.zeitune.olive_insurance_pricing.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "formula")
public class Formula extends VariableItem {
    @Column(name = "expression", nullable = false, columnDefinition = "TEXT")
    private String expression;

    @OneToMany
    private List<VariableItem> variables = new ArrayList<>();

    private UUID coverage;
}
