package sn.zeitune.olive_insurance_pricing.app.entities;

import jakarta.persistence.*;
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
    @ManyToMany
    private List<VariableItem> variables = new ArrayList<>();
}
